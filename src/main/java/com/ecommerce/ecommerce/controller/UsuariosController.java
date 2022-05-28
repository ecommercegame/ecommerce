package com.ecommerce.ecommerce.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.model.UsuarioLogin;
import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;
import com.ecommerce.ecommerce.service.UserService;


@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class UsuariosController {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public ResponseEntity <List<Usuarios>> getAll(){
		
		return ResponseEntity.ok(usuariosRepository.findAll());
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuarios> getById(@PathVariable Long id) {
		return usuariosRepository.findById(id)
			.map(resposta -> ResponseEntity.ok(resposta))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> Autentication(@RequestBody Optional<UsuarioLogin> user)
	{
		return userService.Login(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuarios> postUsuario(@Valid @RequestBody Usuarios usuarios){
		
		return userService.cadastroUsuario(usuarios)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Usuarios> putUsuario(@Valid @RequestBody Usuarios usuario) {
		return userService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		if (!usuariosRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		userService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}
	
}
