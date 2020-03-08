package com.rar.compras.graphql.resolvers.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rar.compras.graphql.resolvers.produto.domain.ProdutoType;
import com.rar.compras.service.ProdutoService;
import com.rar.compras.service.dto.ProdutoDTO;

/**
 * Classe que faz o mapeamento das queries defindas no schema src/main/resources/graphql/produto.graphqls
 * 
 * @author rick
 *
 */
@Component
public class ProdutoQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private ProdutoService produtoService;
	
	public ProdutoType carregarProduto(long id) {
		return produtoService.findById(id)
			.map(prd -> prd.map(ProdutoType.class))
			.orElse(null);
	}
	
	public List<ProdutoType> listarProdutos(ProdutoType produto) {
		return produtoService.findAll(produto.map(ProdutoDTO.class))
			.stream()
			.map(prd -> prd.map(ProdutoType.class))
			.collect(Collectors.toList());
	}
	
}
