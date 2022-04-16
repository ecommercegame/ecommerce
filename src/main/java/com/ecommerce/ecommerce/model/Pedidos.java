package com.ecommerce.ecommerce.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name= "pedidos")
public class Pedidos {

	public Pedidos() {
		super();
	}
	
	
	private static final Long serialVersionUID =1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private Long idPedido;
	
	@NotNull
	@Size(min = 3, max=255)
	@Column(name = "nome_cliente")
	private String nomeCliente;
	
	@Column(name = "qnt_produto")
	private int qntProdutos;
	
	@NotNull
	@Column(name = "valor_total")
	private double valorTotal;
	
	@NotNull
	@Column(name = "confirmacao_pagamento")
	private int confirmacaoPagamento;
	
	@NotNull
	@Column(name= "chave_acesso")	
	private String chaveAcesso;
	
	@NotNull
	@Column(name= "confirmacao_envio")
	private int confirmacaoEnvio;

	public Long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(Long idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
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

	public String getChaveAcesso() {
		return chaveAcesso;
	}

	public void setChaveAcesso(String chaveAcesso) {
		this.chaveAcesso = chaveAcesso;
	}

	public int getConfirmacaoEnvio() {
		return confirmacaoEnvio;
	}

	public void setConfirmacaoEnvio(int confirmacaoEnvio) {
		this.confirmacaoEnvio = confirmacaoEnvio;
	}

	public static Long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
