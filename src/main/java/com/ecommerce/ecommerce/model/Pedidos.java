package com.ecommerce.ecommerce.model;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

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
	
	// Removi o nome do cliente, pois já que as tabelas fazem ligação não tem necessidade
		
	@Column(name = "qnt_produto")
	private int qntProdutos;
	
	@NotNull
	@Column(name = "valor_total")
	private double valorTotal;
	
	@NotNull
	@Column(name = "confirmacao_pagamento")
	private int confirmacaoPagamento;
	
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "chave_acesso", unique = true)	
	private UUID chaveAcesso ;
	
	@NotNull
	@Column(name= "confirmacao_envio")
	private int confirmacaoEnvio;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name= "usuarios_id")
	@JsonManagedReference
	private Usuarios usuarios;
	
	@ManyToMany
	@JoinTable(name= "pedidos_produtos",
	joinColumns = @JoinColumn(name = "pedido_id"),
	inverseJoinColumns= @JoinColumn(name = "produto_id")) //tabela intermediária para guardar quais produtos fazem parte dos pedidos
	@JsonManagedReference
	private List<Produtos> produtos;
	

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public int getQntProdutos() {
		return qntProdutos;
	}

	public void setQntProdutos(int qntProdutos) {
		this.qntProdutos = qntProdutos;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getConfirmacaoPagamento() {
		return confirmacaoPagamento;
	}

	public void setConfirmacaoPagamento(int confirmacaoPagamento) {
		this.confirmacaoPagamento = confirmacaoPagamento;
	}

	@SuppressWarnings("static-access") //gerar chave de acesso aleatória
	public UUID getChaveAcesso() {
		return chaveAcesso.randomUUID();
	}

	public void setChaveAcesso(UUID chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public int getConfirmacaoEnvio() {
		return confirmacaoEnvio;
	}

	public void setConfirmacaoEnvio(int confirmacaoEnvio) {
		this.confirmacaoEnvio = confirmacaoEnvio;
	}

	public Usuarios getUsuarios() {
		return usuarios;
	}

	public void setClientes(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}
	
		
}
