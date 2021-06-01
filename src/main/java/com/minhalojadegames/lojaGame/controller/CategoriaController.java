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

import com.minhalojadegames.lojaGame.model.Categoria;
import com.minhalojadegames.lojaGame.repository.CategoriaRepository;
import com.minhalojadegames.lojaGame.service.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
public class CategoriaController {

	private @Autowired CategoriaRepository repository;
	private @Autowired CategoriaService service;
	
	@GetMapping
	public ResponseEntity<Object> getCategorias(){
		List<Categoria> categorias =repository.findAll();
		
		if (categorias.isEmpty()) {
			return ResponseEntity.status(200).body("Não existe categorias cadastradas!");
		} else {
			return ResponseEntity.ok(categorias);
		}
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getIdCategoria(@PathVariable Long id) {
		return repository.findById(id)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@GetMapping("/buscar/{descricao}")
	public ResponseEntity<List<Categoria>> getAllDescricao(@PathVariable String descricao){
	List<Categoria> listaCategorias = repository.findAllByDescricaoContainingIgnoreCase(descricao);
		if (listaCategorias.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(listaCategorias);
		}
	}
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> salvarCategoria(@RequestBody Categoria descricao){
		return service.salvarCategoria(descricao)
				.map(resp -> ResponseEntity.status(201).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@PutMapping("/alterar/{id}")
	public ResponseEntity<Categoria> putCategoria(@PathVariable long id, @RequestBody Categoria descricao) {
		return 	service.putCategoria(id, descricao)
				.map(resp -> ResponseEntity.status(200).body(resp))
				.orElse(ResponseEntity.status(204).build());
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deleteCategoria(@PathVariable long id) {
		Optional<Categoria> categoria = repository.findById(id);
		if (categoria.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(201).body("Excluido com sucesso");
			
		} else {
			return ResponseEntity.status(200).body("Não existe categoria com esse ID");
		}
	}
}
