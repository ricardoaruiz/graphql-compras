package com.rar.compras.graphql.resolvers.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.service.ClienteService;
import com.rar.compras.service.dto.ClienteDTO;

/**
 * Classe que faz o mapeamento das mutations defindas no schema src/main/resources/graphql/cliente.graphqls
 * 
 * @author rick
 *
 */
@Component
public class ClienteMutationResolver implements GraphQLMutationResolver {

	@Autowired
	private ClienteService clienteService;
	
	public ClienteType salvarCliente(ClienteType cliente) {		
		return clienteService.save(cliente.map(ClienteDTO.class))
			.map(cli -> cli.map(ClienteType.class))
			.orElse(null);
	}
	
	public ClienteType removerCliente(Long id) {		
		return clienteService.delete(id)
			.map(cli -> cli.map(ClienteType.class))
			.orElse(null);
	}
	
}
