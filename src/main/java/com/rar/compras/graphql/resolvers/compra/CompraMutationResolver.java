package com.rar.compras.graphql.resolvers.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rar.compras.graphql.resolvers.compra.domain.CompraInput;
import com.rar.compras.graphql.resolvers.compra.domain.CompraType;
import com.rar.compras.service.CompraService;
import com.rar.compras.service.dto.CompraDTO;

@Component
public class CompraMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private CompraService compraService;
	
	public CompraType criarCompra(CompraInput compra) {
		return compraService.criarCompra(compra.map(CompraDTO.class))
			.map(cpr -> cpr.map(CompraType.class))
			.orElse(null);
	}
	
	public CompraType removerCompra(Long id) {
		return compraService.removerCompra(id)
				.map(cpr -> cpr.map(CompraType.class))
				.orElse(null);
	}
	
}
