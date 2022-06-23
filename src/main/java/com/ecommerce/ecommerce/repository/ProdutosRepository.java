package com.ecommerce.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.ecommerce.model.Produtos;
import com.ecommerce.ecommerce.util.Categorias;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
	
	public List<Produtos>findAllByNomeProdutoContainingIgnoreCase(String nomeProduto);
	
	public List<Produtos>findAllByCategorias(Categorias categorias);
		

}

