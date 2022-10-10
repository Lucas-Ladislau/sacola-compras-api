package me.dio.sacolaComprasApi.resource;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import me.dio.sacolaComprasApi.model.Item;
import me.dio.sacolaComprasApi.model.Sacola;
import me.dio.sacolaComprasApi.resource.dto.ItemDto;
import me.dio.sacolaComprasApi.service.SacolaService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
@Api(value = "/ifood-dev-week/sacolas")
@RestController
@RequestMapping("/ifood-dev-week/sacolas")
@RequiredArgsConstructor
public class SacolaResource {
    private final SacolaService sacolaService;

    @PostMapping
    public Item incluirItemSacola(@RequestBody ItemDto itemDto){
        //@RequestBody serve para captar o itemDto já na requisição post
        return sacolaService.incluirItemSacola(itemDto);
    }

    @GetMapping("/{id}")
    public Sacola verSacola(@PathVariable("id") Long id){
        return sacolaService.verSacola(id);
    }

    //Fazer a atualização de um dado já existente
    @PatchMapping("/fecharSacola/{sacolaId}")
    public Sacola fecharSacola(@PathVariable("sacolaId") Long sacolaId, @RequestParam("formPagamento") int formPagamento){
        return sacolaService.fecharSacola(sacolaId,formPagamento);
    }

    @DeleteMapping("/excluirItem/{idSacola}")
    public Sacola excluirItemSacola(@PathVariable("idSacola") Long idSacola, @RequestParam("id") Long idItem){
        //Excluir um item da sacola pelo id do Item
        return sacolaService.excluirItemSacola(idItem, idSacola);
    }

}
