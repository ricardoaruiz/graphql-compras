package com.rar.compras.graphql.resolvers.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rar.compras.graphql.resolvers.produto.domain.ProdutoType;
import com.rar.compras.service.ProdutoService;
import com.rar.compras.service.dto.ProdutoDTO;

/**
 * Classe que faz o mapeamento das mutations defindas no schema src/main/resources/graphql/produto.graphqls
 * 
 * @author rick
 *
 */
@Component
public class ProdutoMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private ProdutoService produtoService;
	
	public ProdutoType salvarProduto(ProdutoType produto) {
		return produtoService.save(produto.map(ProdutoDTO.class))
			.map(prd -> prd.map(ProdutoType.class))
			.orElse(null);		
	}
	
	public ProdutoType removerProduto(Long id) {
		return produtoService.delete(id)
			.map(prd -> prd.map(ProdutoType.class))
			.orElse(null);
	}
	
}
