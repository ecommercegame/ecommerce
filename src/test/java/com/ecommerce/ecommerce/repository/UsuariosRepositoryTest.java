package com.ecommerce.ecommerce.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.ecommerce.ecommerce.model.Usuarios;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuariosRepositoryTest {

	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@BeforeAll
	void start()
	{
		usuariosRepository.deleteAll();
		usuariosRepository.save(new Usuarios(0L,
				"Gabriel Silva","gabriel@ecommerce.com","96345678963","12345678",null));
		usuariosRepository.save(new Usuarios(0L,
				"Aline Silva","aline@ecommerce.com","10345678963","12345678",null));
		usuariosRepository.save(new Usuarios(0L,
				"Paula Silva","paula@ecommerce.com","85245678963","12345678",null));
		usuariosRepository.save(new Usuarios(0L,
				"Joao","joaol@ecommerce.com","74145678963","12345678",null));
	}
	
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario()
	{
		Optional<Usuarios> usuario = usuariosRepository.findByUsuario("aline@ecommerce.com");
		assertTrue(usuario.get().getUsuario().equals("aline@ecommerce.com"));
	}
	
	@Test	
	@DisplayName("Retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<Usuarios> listaDeUsuarios = usuariosRepository.findAllByNomeContainingIgnoreCase("Silva");	
		assertEquals(3, listaDeUsuarios.size());		
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Gabriel Silva"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Aline Silva"));		
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Paula Silva"));
		
	}
	
		
}
