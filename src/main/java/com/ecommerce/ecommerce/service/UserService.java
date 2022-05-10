package com.ecommerce.ecommerce.service;

import java.util.Optional;
import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ecommerce.ecommerce.model.UsuarioLogin;
import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;

@Service
public class UserService {
	
	@Autowired
	public UsuariosRepository usuarioRepository;
	
	
	public Optional<Usuarios> cadastroUsuario(Usuarios usuario) {
		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();
		usuario.setSenha(criptografarSenha(usuario.getSenha()));
		return Optional.of(usuarioRepository.save(usuario));	
	}
	

	public Optional<Usuarios> atualizarUsuario(Usuarios usuario) {
		
		if(usuarioRepository.findById(usuario.getIdUsuario()).isPresent()) {			
			
			Optional<Usuarios> buscarUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());
			
			if ( (buscarUsuario.isPresent()) && ( buscarUsuario.get().getIdUsuario() != usuario.getIdUsuario()))
				throw new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Usuário já existe!", null);
			usuario.setSenha(criptografarSenha(usuario.getSenha()));			
			return Optional.ofNullable(usuarioRepository.save(usuario));			
		}				
		return Optional.empty();
	
	}
	
	public Optional<UsuarioLogin> Login(Optional<UsuarioLogin> usuarioLogin) {		
		Optional<Usuarios> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

		if (usuario.isPresent()) {			
			if (compararSenhas(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
				
				usuarioLogin.get().setIdUsuario(usuario.get().getIdUsuario());
				usuarioLogin.get().setNome(usuario.get().getNome());
				usuarioLogin.get().setCpfUsuario(usuario.get().getCpfUsuario());	
				usuarioLogin.get().setToken(gerarBasicToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
				usuarioLogin.get().setSenha(usuario.get().getSenha());

				return usuarioLogin;

			}
		}			
		return Optional.empty();
		
	}
	
	/*Criptografar senha
	 Instancia um objeto da Classe BCryptPasswordEncoder para criptografar a senha do usuário.*/
	private String criptografarSenha(String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.encode(senha);
	}

	
	/*Comparar senha Checa se a senha enviada, depois de criptografada, é igual a senha
	gravada no Banco de Dados.*/
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();		
		return encoder.matches(senhaDigitada, senhaBanco);
	}
	
	
	/*Método Gerar Basic Token	
	* A primeira linha, monta uma String (token) seguindo o padrão Basic, através 
	* da concatenação de caracteres que será codificada (Não criptografada) no formato 
	* Base64, através da Dependência Apache Commons Codec.
	* Essa String tem o formato padrão: <username>:<password> que não pode ser
	* alterado */
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}
	
}
