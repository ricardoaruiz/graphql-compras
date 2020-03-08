package com.rar.compras.service.dto;

import java.util.Optional;

import com.rar.compras.model.entity.Cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ClienteDTO extends BaseDTO {

	private Long id;
	private String nome;
	private String email;
	
	private ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	public static ClienteDTO fromEntity(Cliente cliente) {		
		return Optional.ofNullable(cliente)
				.map(cli -> new ClienteDTO(cli))
				.orElse(null);
	}
	
}
