package com.rar.compras.model.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	
	private String nome;	
	private BigDecimal valor;
	
	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<Compra> compras;

	public Produto(String nome, BigDecimal valor) {
		super();
		this.nome = nome;
		this.valor = valor;
	}	
	
}
