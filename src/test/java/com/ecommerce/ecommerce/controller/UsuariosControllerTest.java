package com.ecommerce.ecommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;
import com.ecommerce.ecommerce.service.UserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuariosControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@BeforeAll
	void start()
	{
		usuariosRepository.deleteAll();
	}
	
	
	@Test
	@Order(1)
	@DisplayName("Cadastrar um Usuário")
	public void deveCriarUmUsuario()
	{
		HttpEntity<Usuarios> requisicao = new HttpEntity<Usuarios>(new Usuarios(0L,
				"Gabriel","gabriel@ecommerce.com","96345678963","12345678",null));
		ResponseEntity<Usuarios> resposta = testRestTemplate
				.exchange("/usuarios/cadastrar",HttpMethod.POST,requisicao,Usuarios.class);
		
		assertEquals(HttpStatus.CREATED,resposta.getStatusCode());
		assertEquals(requisicao.getBody().getNome(),resposta.getBody().getNome());
		assertEquals(requisicao.getBody().getUsuario(),resposta.getBody().getUsuario());
		assertEquals(requisicao.getBody().getCpfUsuario(),resposta.getBody().getCpfUsuario());
		
		
	}
	
	@Test
	@Order(2)
	@DisplayName("Não deve permitir duplicação do Usuário")
	public void naoDeveDuplicarUsuario() {
	
		userService.cadastroUsuario(new Usuarios(0L,
				"Adriana","adriana@ecommerce.com","12345678963","12345678",null));
		HttpEntity<Usuarios> corpoRequisicao = new HttpEntity<Usuarios>(new Usuarios(0L,
				"Adriana","adriana@ecommerce.com","12345678963","12345678",null));
		ResponseEntity<Usuarios> corpoResposta = testRestTemplate
				.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuarios.class);

			
			assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	}
	
	@Test
	@Order(3)
	@DisplayName("Alterar um usuário")
	public void deveAlterarUmUsuario()
	{
		Optional<Usuarios> usuarioCreate = userService.cadastroUsuario(new Usuarios(0L,
				"Joyce","joyce@ecommerce.com","22345678963","12345678",null));
		Usuarios usuarioUpdate = new Usuarios(usuarioCreate.get().getIdUsuario(),
				"Joyce B","joyce@ecommerce.com","22345678963","Adf345678",null);
		HttpEntity<Usuarios> requisicao = new HttpEntity<Usuarios>(usuarioUpdate);
		ResponseEntity<Usuarios> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/cadastrar",HttpMethod.PUT,requisicao,Usuarios.class);
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(),resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(),resposta.getBody().getUsuario());
		assertEquals(usuarioUpdate.getCpfUsuario(),resposta.getBody().getCpfUsuario());
	}
	
	@Test
	@Order(4)
	@DisplayName("Listar todos os usuários")
	public void deveMostrarTodosUsuarios()
	{
		userService.cadastroUsuario(new Usuarios(0L,
				"Laura","laura@ecommerce.com","45345678963","98745678",null));
		userService.cadastroUsuario(new Usuarios(0L,
				"Bianca","bianca@ecommerce.com","46325678963","85245678",null));
		ResponseEntity<String> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuarios/all", HttpMethod.GET,null,String.class);
		assertEquals(HttpStatus.OK,resposta.getStatusCode());
	}
}
