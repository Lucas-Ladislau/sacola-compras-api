package me.dio.sacolaComprasApi.repository;

import me.dio.sacolaComprasApi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
