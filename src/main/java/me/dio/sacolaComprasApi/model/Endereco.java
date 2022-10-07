package me.dio.sacolaComprasApi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 06/10/2022.
 */
@AllArgsConstructor
@Builder
@Data
@Embeddable
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String complemento;
}
