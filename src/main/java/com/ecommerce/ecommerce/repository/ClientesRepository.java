package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes,Long> {

	public List<Clientes>findAllByNomeUsuarioContainingIgnoreCase(String nomeUsuario);
}
