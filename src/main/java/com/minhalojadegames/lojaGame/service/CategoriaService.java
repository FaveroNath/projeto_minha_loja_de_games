package com.minhalojadegames.lojaGame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhalojadegames.lojaGame.model.Categoria;
import com.minhalojadegames.lojaGame.repository.CategoriaRepository;

@Service
public class CategoriaService {
	private @Autowired CategoriaRepository repository;
	
	/**
	 * Método criado para verificar se existe o elemento a ser alterado. 
	 * @param id
	 * @param descricao
	 * @return Um optiona caso exista ou optional vazio caso contrátio.
	 * @since V.1.0
	 * @author Nathalia Favero
	 */
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
	
	/**
	 * Verifica se o elemento a ser cadastrado existe no sistema.
	 * @param descricao
	 * @return Um optional vazio caso exista ou um optional.
	 * @since V.1.0
	 * @author Nathalia Favero
	 */
	public Optional<Categoria> salvarCategoria(Categoria descricao){
		Optional<Categoria> categoria = repository.findByDescricao(descricao.getDescricao());
		if (categoria.isPresent()) {
			return Optional.empty();
		} else{
			return Optional.ofNullable(repository.save(descricao));
		}
	}
	
}
