package com.rar.compras.graphql.resolvers.compra;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rar.compras.graphql.resolvers.compra.domain.CompraInput;
import com.rar.compras.graphql.resolvers.compra.domain.CompraType;
import com.rar.compras.service.CompraService;
import com.rar.compras.service.dto.CompraDTO;

/**
 * Classe que faz o mapeamento das queries defindas no schema src/main/resources/graphql/compra.graphqls
 * 
 * @author rick
 *
 */
@Component
public class CompraQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private CompraService compraService;
		
	public CompraType carregarCompra(Long id) {
		return compraService.findById(id)
			.map(cpr -> cpr.map(CompraType.class))
			.orElse(null);
	}
	
	public List<CompraType> listarCompras(CompraInput compra) {
		return compraService.findAll(compra.map(CompraDTO.class))
			.stream()
			.map(cpr -> cpr.map(CompraType.class))
			.collect(Collectors.toList());
	}
	
}
