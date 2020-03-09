package com.rar.compras.graphql.resolvers.compra.domain;

import java.util.Date;

import com.rar.compras.graphql.domain.BaseType;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.graphql.resolvers.produto.domain.ProdutoType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompraType extends BaseType {

	private Long id;
	private Date data;
	private Integer quantidade;
	private String status;
	private ClienteType cliente;
	private ProdutoType produto;
	
}
