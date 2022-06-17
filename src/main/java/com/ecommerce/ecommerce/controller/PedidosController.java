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

import com.ecommerce.ecommerce.model.Pedidos;
import com.ecommerce.ecommerce.repository.PedidosRepository;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin("*")
public class PedidosController {
	
	@Autowired
	private PedidosRepository pedidosRepository;
	
	@GetMapping
	public ResponseEntity<List<Pedidos>>GetAll(){
				return ResponseEntity.ok(pedidosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pedidos> GetById(@PathVariable Long id){
		return pedidosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
		
	
	@PostMapping
	public ResponseEntity<Pedidos> post (@RequestBody Pedidos pedidos){
		return ResponseEntity.status(HttpStatus.CREATED).body(pedidosRepository.save(pedidos));
	}
	
	@PutMapping
	public ResponseEntity<Pedidos> put (@RequestBody Pedidos pedidos){
		return ResponseEntity.status(HttpStatus.OK).body(pedidosRepository.save(pedidos));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		pedidosRepository.deleteById(id);
	}

}
