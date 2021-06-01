package com.minhalojadegames.lojaGame.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhalojadegames.lojaGame.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Optional<Produto> findAllByTituloContainingIgnoreCase(String titulo);
	Optional<Produto> findByTitulo(String titulo);
}
