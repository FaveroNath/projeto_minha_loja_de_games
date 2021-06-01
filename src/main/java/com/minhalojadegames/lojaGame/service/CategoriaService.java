package com.minhalojadegames.lojaGame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhalojadegames.lojaGame.model.Categoria;
import com.minhalojadegames.lojaGame.repository.CategoriaRepository;

@Service
public class CategoriaService {
	private @Autowired CategoriaRepository repository;
	
	public Optional<Categoria> putCategoria(Long id, Categoria descricao){
		Optional<Categoria> categoriaAntiga = repository.findById(id);
		
		if (categoriaAntiga.isPresent()) {
			Categoria newCategoria = categoriaAntiga.get();
			newCategoria.setDescricao(descricao.getDescricao());
			return Optional.ofNullable(repository.save(newCategoria));
		} else {
			return Optional.empty();
		}
	}
	
	public Optional<Categoria> salvarCategoria(Categoria descricao){
		Optional<Categoria> categoria = repository.findByDescricao(descricao.getDescricao());
		if (categoria.isPresent()) {
			return Optional.empty();
		} else{
			return Optional.ofNullable(repository.save(descricao));
		}
	}
	
}
