package com.ecommerce.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Long> {

	public Optional<Usuarios>findByUsuario(String usuario);
}
