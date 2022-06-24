package com.ecommerce.ecommerce.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecommerce.ecommerce.util.Categorias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="produtos")
public class Produtos {

	
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
	
	private @Enumerated(EnumType.STRING) Categorias categorias;
	
	@NotNull
	private String imagem;
	
	@OneToMany(mappedBy="produtos", fetch = FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JsonIgnore
	private List<Pedidos> pedidos;

	
	public Produtos () {
		super ();
	}
	
	

	/*public Produtos(Long idProduto, String nomeProduto, String descricaoProduto, double valorProduto, int estoque,
			String categorias, String imagem, Pedidos pedidos) {
		this.idProduto = idProduto;
		this.nomeProduto = nomeProduto;
		this.descricaoProduto = descricaoProduto;
		this.valorProduto = valorProduto;
		this.estoque = estoque;
		this.categorias = categorias;
		this.imagem = imagem;
		this.pedidos = pedidos;
	}*/





	public Long getIdProduto() {
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


	public Categorias getCategorias() {
		return categorias;
	}


	public void setCategorias(Categorias categorias) {
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