package me.dio.sacolaComprasApi.service;

import me.dio.sacolaComprasApi.model.Item;
import me.dio.sacolaComprasApi.model.Sacola;
import me.dio.sacolaComprasApi.resource.dto.ItemDto;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
public interface SacolaService {
    Item incluirItemSacola(ItemDto itemDto);
    Sacola verSacola(Long id);
    Sacola fecharSacola(Long id, int formPagamento);
    Sacola excluirItemSacola(Long id, Long idSacola);
}
