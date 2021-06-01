package com.minhalojadegames.lojaGame.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhalojadegames.lojaGame.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

	List<Categoria> findAllByDescricaoContainingIgnoreCase(String descricao);
	Optional<Categoria> findByDescricao(String descricao);
}
