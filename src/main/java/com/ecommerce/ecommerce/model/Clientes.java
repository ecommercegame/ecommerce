package com.ecommerce.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="clientes")
public class Clientes {
	
	public Clientes() {
		super();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_Cliente")
	private Long idCliente;
	
	@NotNull
	@Size(min=1,max=255)
	@Column(name="nome_cliente")
	private String nomeUsuario;
	
	@NotNull
	@Size(min=11,max=11)
	@Column(name="cpf_cliente")
	private String cpfCliente;
	
	@NotNull
	@Size(min=1,max=255)
	@Column(name="email_cliente")
	private String emailCliente;
	
	
	@Size(min=4,max=20)
	@Column(name="senha_cliente")
	@JsonIgnore // não mostrar a senha/ deixar a coluna oculta
	private String senhaCliente;
	
	@OneToMany(mappedBy = "clientes")	
	@JsonBackReference
	private List<Pedidos> pedidos;

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeCliente(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfCliente() {
		return cpfCliente;
	}

	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getSenhaCliente() {
		return senhaCliente;
	}

	public void setSenhaCliente(String senhaCliente) {
		this.senhaCliente = senhaCliente;
	}

	public List<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
