package com.ecommerce.ecommerce.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.ecommerce.model.Usuarios;
import com.ecommerce.ecommerce.repository.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	public UsuariosRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuarios> user = userRepository.findByUsuario(username);
		user.orElseThrow(() -> new UsernameNotFoundException(username+"not found"));
		return user.map(UserDetailsImpl:: new).get();
	}
	

}
