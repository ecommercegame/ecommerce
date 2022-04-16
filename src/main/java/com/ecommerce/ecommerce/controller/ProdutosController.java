package com.ecommerce.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.Produtos;
import com.ecommerce.ecommerce.repository.ProdutosRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("*")

public class ProdutosController {
	
	@Autowired
	private ProdutosRepository produtosRepository;
	
	@GetMapping
	public ResponseEntity<List<Produtos>>GetAll(){
		
		return ResponseEntity.ok(produtosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produtos>GetById(@PathVariable Long id){
		
		return produtosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>>GetByNome(@PathVariable String nome){
		
		return ResponseEntity.ok(produtosRepository.findAllByNomeProdutoContainingIgnoreCase(nome));
	}
	
	@GetMapping("/categorias/{categorias}")
	public ResponseEntity<List<Produtos>>GetByCategorias(@PathVariable String categorias){
		
		return ResponseEntity.ok(produtosRepository.findAllByCategoriasContainingIgnoreCase(categorias));
	}
	
	@PostMapping
	public ResponseEntity<Produtos> post (@RequestBody Produtos produtos){
		
		return ResponseEntity.status(HttpStatus.CREATED).body(produtosRepository.save(produtos));
	}
	
	@PutMapping
	public ResponseEntity<Produtos> put (@RequestBody Produtos produtos){
		
		return ResponseEntity.status(HttpStatus.OK).body(produtosRepository.save(produtos));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		
		produtosRepository.deleteById(id);
	}
	

}