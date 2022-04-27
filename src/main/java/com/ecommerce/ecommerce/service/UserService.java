package com.ecommerce.ecommerce.service;

import java.util.Optional;
import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.UsuarioLogin;
import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;

@Service
public class UserService {
	
	@Autowired
	public UsuariosRepository usuarioRepository;
	
	
	public Usuarios cadastroUsuario(Usuarios usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String passEncoder = encoder.encode(usuario.getSenha());
		usuario.setSenha(passEncoder);
		
		return usuarioRepository.save(usuario);
	}
	
	
	public Optional<UsuarioLogin> login(Optional<UsuarioLogin> usuario) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<Usuarios> user = usuarioRepository.findByUsuario(usuario.get().getUsuario());
	
		if(usuario.isPresent()) {
			if(encoder.matches(usuario.get().getSenha(), user.get().getSenha())) {
				
				String auth = usuario.get().getUsuario() + ":" + usuario.get().getSenha();
				byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic"+ new String(encodeAuth);

				usuario.get().setToken(authHeader);
				usuario.get().setNome(user.get().getNome());
				return usuario;
			}
		}
		
		
		return null;
	}

}
