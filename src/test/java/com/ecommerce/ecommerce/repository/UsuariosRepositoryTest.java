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
	usuariosRepository.save(new Usuarios(0L,"Maria","roba_lanche@email.com","40589652073", "1234567", null ));
	usuariosRepository.save(new Usuarios(0L,"Luana","luana_tenguan@email.com","40589652363", "1234567", null));
	usuariosRepository.save(new Usuarios(0L,"Guilherme","fuba_cremoso@email.com", "40589152063", "1234567", null));
	usuariosRepository.save(new Usuarios(0L,"Jefferson","jefferson_souza@email.com","40582652063", "1234567",null));
	
	}
	
	@Test
	@DisplayName("Retorna 1 usuário")
	public void deveRetornarUmUsuario()
	{
		Optional<Usuarios> usuario = usuariosRepository.findByUsuario("luana_tenguan@email.com");
		assertTrue(usuario.get().getUsuario().equals("luana_tenguan@email.com"));
	}
	
	@Test
	@DisplayName("Retorna 3 usuário")
	public void deveRetornarTresUsuarios()	
	{
		List<Usuarios> listaDeUsuarios = usuariosRepository.findAllByNomeContainingIgnoreCase("Colmeia");
		assertEquals(3,listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Maria"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Luana"));
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Guilherme"));
	}
}
