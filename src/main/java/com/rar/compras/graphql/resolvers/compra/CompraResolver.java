package com.rar.compras.graphql.resolvers.compra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.graphql.resolvers.compra.domain.CompraType;
import com.rar.compras.graphql.resolvers.produto.domain.ProdutoType;
import com.rar.compras.service.ClienteService;
import com.rar.compras.service.ProdutoService;

/**
 * Classe que resolve os campos a serem retornados para o tipo CompraType
 * caso eles sejam solicitados em uma query
 * 
 * @author rick
 *
 */

@Component
public class CompraResolver implements GraphQLResolver<CompraType>{

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProdutoService produtoService;
	
	public ClienteType cliente(CompraType compra) {
		return clienteService.findById(compra.getCliente().getId())
				.map(cli -> cli.map(ClienteType.class))
				.orElse(null);
	}
	
	public ProdutoType produto(CompraType compra) {
		return produtoService.findById(compra.getProduto().getId())
				.map(prd -> prd.map(ProdutoType.class))
				.orElse(null);
	}
	
}
