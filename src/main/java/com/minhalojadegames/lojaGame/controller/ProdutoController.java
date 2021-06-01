package com.minhalojadegames.lojaGame.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.minhalojadegames.lojaGame.model.Produto;
import com.minhalojadegames.lojaGame.repository.ProdutoRepository;
import com.minhalojadegames.lojaGame.service.ProdutoService;

@RestController
@RequestMapping("api/v1/produtos")
public class ProdutoController {
	
	private @Autowired ProdutoRepository repository;
	private @Autowired ProdutoService service;
	
	@GetMapping
	public ResponseEntity<Object> getProdutos(){
		List<Produto> produtos =repository.findAll();
		if (produtos.isEmpty()) {
			return ResponseEntity.status(200).body("Não existe produtos cadastrados");
		}
		return ResponseEntity.status(200).body(produtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getIdProduto(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/buscarProduto/{titulo}")
	public ResponseEntity<Produto> getTituloProduto(@PathVariable String titulo){
		return repository.findByTitulo(titulo)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Object> setProduto(@RequestBody Produto produto){
		return service.salvarProduto(produto)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
		
	}
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Object> putProdutos(@PathVariable long id, @RequestBody Produto produto){
		return service.alterarProduto(id, produto)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deleteProduto(@PathVariable long id){
		Optional<Produto> produtoExistente = repository.findById(id);
		if(produtoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Produto excluído com sucesso");
		} else {
			return ResponseEntity.status(200).body("Produto inexistente no sistema");
		}
	}
}
