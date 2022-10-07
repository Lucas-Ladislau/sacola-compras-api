package me.dio.sacolaComprasApi.repository;

import me.dio.sacolaComprasApi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 07/10/2022.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
