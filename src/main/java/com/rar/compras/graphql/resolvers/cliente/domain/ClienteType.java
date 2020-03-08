package com.rar.compras.graphql.resolvers.cliente.domain;

import java.util.List;

import com.rar.compras.graphql.domain.BaseType;
import com.rar.compras.graphql.resolvers.compra.domain.CompraType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClienteType extends BaseType {

	private Long id;	
	private String nome;	
	private String email;
	private List<CompraType> compras;
	
}
