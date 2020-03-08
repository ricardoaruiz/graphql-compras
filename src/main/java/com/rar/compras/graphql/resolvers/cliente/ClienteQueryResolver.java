package com.rar.compras.graphql.resolvers.cliente;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.service.ClienteService;
import com.rar.compras.service.dto.ClienteDTO;

/**
 * Classe que faz o mapeamento das queries defindas no schema src/main/resources/graphql/cliente.graphqls
 * 
 * @author rick
 *
 */
@Component
public class ClienteQueryResolver implements GraphQLQueryResolver {

	@Autowired
	private ClienteService clienteService;
			
	public ClienteType carregarCliente(long id) {				
		return clienteService.findById(id)
			.map(cli -> cli.map(ClienteType.class))
			.orElse(null);
	}
	
	public List<ClienteType> listarClientes(ClienteType cliente) {	
		return clienteService.findAll(cliente.map(ClienteDTO.class))
			.stream()
			.map(cli -> cli.map(ClienteType.class))
			.collect(Collectors.toList());
	}
	
}
