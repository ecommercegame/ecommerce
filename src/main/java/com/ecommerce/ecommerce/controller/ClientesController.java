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

import com.ecommerce.ecommerce.model.Clientes;
import com.ecommerce.ecommerce.repository.ClientesRepository;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("*")
public class ClientesController {
	@Autowired
	private ClientesRepository clientesRepository;
	
	@GetMapping
	public ResponseEntity<List<Clientes>>GetAll(){
				return ResponseEntity.ok(clientesRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Clientes> GetById(@PathVariable Long id){
		return clientesRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Clientes>>GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(clientesRepository.findAllByNomeUsuarioContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity<Clientes> post (@RequestBody Clientes clientes){
		return ResponseEntity.status(HttpStatus.CREATED).body(clientesRepository.save(clientes));
	}
	
	@PutMapping
	public ResponseEntity<Clientes> put (@RequestBody Clientes clientes){
		return ResponseEntity.status(HttpStatus.OK).body(clientesRepository.save(clientes));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		clientesRepository.deleteById(id);
	}
}
