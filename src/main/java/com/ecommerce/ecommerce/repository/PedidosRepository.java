package com.ecommerce.ecommerce.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Pedidos;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, Long> {
	
	public List<Pedidos>findAllByIdPedido(int idProduto);

}
