package com.minhalojadegames.lojaGame.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhalojadegames.lojaGame.model.Produto;
import com.minhalojadegames.lojaGame.repository.ProdutoRepository;

@Service
public class ProdutoService {

	private @Autowired ProdutoRepository repository;
	
	public Optional<Object> salvarProduto(Produto produto){
		Optional<Produto> produtos = repository.findByTitulo(produto.getTitulo());
		if (produtos.isPresent()) {
			return Optional.empty();
		}
		return Optional.ofNullable(repository.save(produto));
	}
	
	public Optional<Object> alterarProduto(long id, Produto produto){
		Optional<Produto> produtoExistente = repository.findById(id);
		if (produtoExistente.isPresent()) {
			Produto newProduto = produtoExistente.get();
			newProduto.setTitulo(produto.getTitulo());
			newProduto.setDescricao(produto.getDescricao());
			newProduto.setCategoria(produto.getCategoria());
			return Optional.ofNullable(repository.save(newProduto));
		}
		return Optional.empty();
	}
	
}
