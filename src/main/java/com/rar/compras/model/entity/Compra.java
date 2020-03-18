package com.rar.compras.model.entity;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Cacheable
@Data
@NoArgsConstructor
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date data;
	private Integer quantidade;
	private String status;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="CLIENTE_ID")
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="PRODUTO_ID")
	private Produto produto;

	public Compra(Date data, int quantidade, String status, Cliente cliente, Produto produto) {
		super();
		this.data = data;
		this.quantidade = quantidade;
		this.status = status;
		this.cliente = cliente;
		this.produto = produto;
	}
	
}
