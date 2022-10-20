package com.capijava.capijava.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class ProdutosModel {

	//Tabela Produtos

	//Id
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	//Nome
	@NotBlank
	@Size (min = 1, max = 200)
	private String nome;

	//Descrição 
	@NotBlank
	@Size (min = 5 , max = 1000)
	private String descricao;

	//Preço
	@NotNull
	private Float preco;

	//Tamanho 
	@NotBlank
	@Size (min = 1 , max = 20)
	private String tamanho;

	//Quantidade
	@NotNull
	private int quantidade;

	//Foto
	private String foto; //Sem especificações 
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private CategoriaModel categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private UsuarioModel usuario;
		
	
	public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

	public String getTamanho() {
		return tamanho;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public CategoriaModel getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModel categoria) {
		this.categoria = categoria;
	}

	
	
	
}
