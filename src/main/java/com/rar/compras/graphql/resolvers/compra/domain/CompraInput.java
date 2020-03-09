package com.rar.compras.graphql.resolvers.compra.domain;

import java.util.Date;

import com.rar.compras.graphql.domain.BaseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompraInput extends BaseType {

	private Long id;
	private Date data;
	private Integer quantidade;
	private String status;
	private Long clienteId;
	private Long produtoId;
	
}
