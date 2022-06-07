package com.ecommerce.ecommerce.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name= "pedidos")
public class Pedidos {
	
	public Pedidos() {
		super();
	}
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
			
	@Column(name = "mais_vendidos")
	private int maisVendidos;
	
	@NotNull
	@Column(name = "valor_total")
	private double valorTotal;
		
	@ManyToOne 
	@JoinColumn(name= "usuarios_id")
	private Usuarios usuarios;
	
	@OneToMany (mappedBy = "pedidos", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name= "id_produtos")
	@JsonIgnore
	private List<Produtos> produtos;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public int getMaisVendidos() {
		return maisVendidos;
	}

	public void setMaisVendidos(int maisVendidos) {
		this.maisVendidos = maisVendidos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}
	
	
}



	

	