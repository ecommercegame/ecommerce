package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

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

import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuariosController {
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@GetMapping
	public ResponseEntity<List<Usuarios>>GetAll(){
				return ResponseEntity.ok(usuariosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> GetById(@PathVariable Long id){
		return usuariosRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<Optional<Usuarios>>GetByNome(@PathVariable String nome){
		return ResponseEntity.ok(usuariosRepository.findByUsuario(nome));
	}
	
	@PostMapping
	public ResponseEntity<Usuarios> post (@RequestBody Usuarios clientes){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuariosRepository.save(clientes));
	}
	
	@PutMapping
	public ResponseEntity<Usuarios> put (@RequestBody Usuarios clientes){
		return ResponseEntity.status(HttpStatus.OK).body(usuariosRepository.save(clientes));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		usuariosRepository.deleteById(id);
	}
}
