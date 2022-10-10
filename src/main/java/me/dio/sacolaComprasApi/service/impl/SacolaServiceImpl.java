package me.dio.sacolaComprasApi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaComprasApi.enumeration.FormaPagamento;
import me.dio.sacolaComprasApi.model.Item;
import me.dio.sacolaComprasApi.model.Restaurante;
import me.dio.sacolaComprasApi.model.Sacola;
import me.dio.sacolaComprasApi.repository.ProdutoRepository;
import me.dio.sacolaComprasApi.repository.SacolaRepository;
import me.dio.sacolaComprasApi.resource.dto.ItemDto;
import me.dio.sacolaComprasApi.service.SacolaService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
@Service
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    public Item incluirItemSacola(ItemDto itemDto) {
        Sacola sacola = verSacola(itemDto.getSacolaId());

        if(sacola.isFechada()){
            throw new RuntimeException("A sacola está fechada");
        }

        Item itemInserido = Item.builder()
                .quantidade(itemDto.getQuantidade())
                .sacola(sacola)
                .produto(produtoRepository.findById(itemDto.getProdutoId()).orElseThrow(() -> {
                    throw new RuntimeException("Essa produto não existe");
                }))
                .build();

        List<Item> itensSacola = sacola.getItens();
        if(itensSacola.isEmpty()){
            itensSacola.add(itemInserido);
        }else{
            //get(0) método list para pegar [0]
            Restaurante restauranteAtual = itensSacola.get(0).getProduto().getRestaurante();
            Restaurante restauranteItemParaSerAdicionado = itensSacola.get(0).getProduto().getRestaurante();
            if (restauranteAtual.equals(restauranteItemParaSerAdicionado)) {
                itensSacola.add(itemInserido);
            }else{
                throw new RuntimeException("O Item a ser adicionado deve ser do mesmo restaurante. " +
                        "Esvazie ou abra um novo carrinho/sacola");
            }
        }

        List<Double> valorDosItens = new ArrayList<>();
        for (Item item:itensSacola) {
            double valorItem = item.getProduto().getValorUnitario() * item.getQuantidade();
            valorDosItens.add(valorItem);
        }

        double totalSacola = valorDosItens.stream().mapToDouble(valorItens -> valorItens).sum();
        sacola.setValorTotal(totalSacola);

        sacolaRepository.save(sacola);
        return itemInserido;
    }

    @Override
    public Sacola verSacola(Long id) {
        return sacolaRepository.findById(id).orElseThrow(
                () -> {
                    throw new RuntimeException("Essa sacola não existe");
                }
        );
    }

    @Override
    public Sacola fecharSacola(Long id, int numeroformPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()){
            throw new RuntimeException("Inclua pelo menos um item na sacola");
        }
        FormaPagamento formaPagamento =
                numeroformPagamento == 0 ? FormaPagamento.DINHEIRO : FormaPagamento.MAQUINETA;

        sacola.setFormaPagamento(formaPagamento);
        sacola.setFechada(true);
        return sacolaRepository.save(sacola);
    }
}
