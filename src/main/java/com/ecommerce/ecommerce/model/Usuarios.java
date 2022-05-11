package com.ecommerce.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "usuarios")
public class Usuarios {

	public Usuarios() {
		super();
	}

	
	public Usuarios(Long idUsuario, String nome,
			 String usuario,  String cpfUsuario,
			 String senha, List<Pedidos> pedidos) {
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.usuario = usuario;
		this.cpfUsuario = cpfUsuario;
		this.senha = senha;
		this.pedidos = pedidos;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Usuario")
	private Long idUsuario;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "nome")
	private String nome;
	
	@Schema(example="example@email.com.br")
	@NotNull(message="O atributo usuário é obrigatório")
	@Email(message="O atributo usuário deve ser um email válido")
	private String usuario; // email do usuario, remover emaiUsuario

	@NotNull
	@Size(min = 11, max = 11)
	@Column(name = "cpf_usuario")
	private String cpfUsuario;	

	@Size(min = 4, max = 20)
	@Column(name = "senha")
	@NotNull(message = "A senha deve conter entre 4 a 20 caracteres")
	private String senha;

	@OneToMany(mappedBy = "usuarios")
	@JsonBackReference
	private List<Pedidos> pedidos;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
		
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getCpfUsuario() {
		return cpfUsuario;
	}

	public void setCpfUsuario(String cpfUsuario) {
		this.cpfUsuario = cpfUsuario;
	}


	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Pedidos> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedidos> pedidos) {
		this.pedidos = pedidos;
	}

}
