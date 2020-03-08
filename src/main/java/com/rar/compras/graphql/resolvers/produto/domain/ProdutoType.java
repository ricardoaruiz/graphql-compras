package com.rar.compras.graphql.resolvers.produto.domain;

import java.math.BigDecimal;

import com.rar.compras.graphql.domain.BaseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoType extends BaseType {

	private Long id;
	private String nome;
	private BigDecimal valor;
	
}
