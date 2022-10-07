package me.dio.sacolaComprasApi.service.impl;

import lombok.RequiredArgsConstructor;
import me.dio.sacolaComprasApi.model.Item;
import me.dio.sacolaComprasApi.model.Sacola;
import me.dio.sacolaComprasApi.repository.SacolaRepository;
import me.dio.sacolaComprasApi.resource.dto.ItemDto;
import me.dio.sacolaComprasApi.service.SacolaService;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
@RequiredArgsConstructor
public class SacolaServiceImpl implements SacolaService {
    private final SacolaRepository sacolaRepository;
    @Override
    public Item incluirItemSacola(ItemDto itemDto) {
        return null;
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
    public Sacola fecharSacola(Long id, int formPagamento) {
        Sacola sacola = verSacola(id);
        if (sacola.getItens().isEmpty()){
            throw new RuntimeException("Inclua pelo menos um item na sacola");
        }
        return null;
    }
}
