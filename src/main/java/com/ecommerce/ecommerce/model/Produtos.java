package com.ecommerce.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="produtos")
public class Produtos {
	
	public Produtos () {
		super ();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Produto")
	private Long idProduto;
	
	@NotNull
	@Size(min=1,max=255)
	@Column(name="nome_Produto")
	private String nomeProduto;
	
	@NotNull
	@Size(min=1)
	@Column(name="descricao_Produto")
	private String descricaoProduto;
	
	
	@NotNull
	@Column(name="valor_Produto")
	private double valorProduto;
	
	
	@Column(name="estoque")
	private int estoque;
	
	@NotNull
	@Column(name="categorias")
	private String categorias;
	
	@NotNull
	private String imagem;
	
	@ManyToOne
	@JoinColumn(name = "id_pedidos")
	private List<Pedidos> pedidos;


	public long getIdProduto() {
		return idProduto;
	}


	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}


	public String getNomeProduto() {
		return nomeProduto;
	}


	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}


	public String getDescricaoProduto() {
		return descricaoProduto;
	}


	public void setDescricaoProduto(String descricaoProduto) {
		this.descricaoProduto = descricaoProduto;
	}


	public double getValorProduto() {
		return valorProduto;
	}


	public void setValorProduto(double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public int getEstoque() {
		return estoque;
	}


	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}


	public String getCategorias() {
		return categorias;
	}


	public void setCategorias(String categorias) {
		this.categorias = categorias;
	}


	public List<Pedidos> getPedidos() {
		return pedidos;
	}


	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}


	public String getImagem() {
		return imagem;
	}


	public void setImagem(String imagem) {
		this.imagem = imagem;
	}
	
	

	
}