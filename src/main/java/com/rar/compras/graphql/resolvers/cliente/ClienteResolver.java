package com.rar.compras.graphql.resolvers.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.graphql.resolvers.compra.domain.CompraType;
import com.rar.compras.service.CompraService;

/**
 * Classe que resolve os campos a serem retornados para o tipo ClienteType
 * caso eles sejam solicitados em uma query
 * 
 * @author rick
 *
 */
@Component
public class ClienteResolver implements GraphQLResolver<ClienteType>{

	@Autowired
	private CompraService compraService;

	
	public List<CompraType> compras (ClienteType cliente) {
		return compraService.findAllByClienteId(cliente.getId())
			.stream()
			.map(compra -> compra.map(CompraType.class))
			.collect(Collectors.toList());
	}
	
}
