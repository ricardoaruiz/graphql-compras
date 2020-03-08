package com.rar.compras.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rar.compras.model.entity.Cliente;
import com.rar.compras.model.repository.ClienteRepository;
import com.rar.compras.service.dto.ClienteDTO;
import com.rar.compras.service.dto.CompraDTO;

@Service
public class ClienteService {

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional(readOnly = true)
	public Optional<ClienteDTO> findById(long id) {				
		return clienteRepository.findById(id)
			.map(cli -> ClienteDTO.fromEntity(cli));
	}
	
	@Transactional(readOnly = true)
	public List<ClienteDTO> findAll(ClienteDTO clienteDTO) {
		
		Example<Cliente> filter = baseService.getExampleFilter(clienteDTO, Cliente.class);
			
		return clienteRepository.findAll(filter)
			.stream()
			.map(cli -> ClienteDTO.fromEntity(cli))
			.collect(Collectors.toList());
	}
	
	@Transactional
	public Optional<ClienteDTO> save(ClienteDTO clienteDTO) {		
		Cliente clienteSalvo = clienteRepository.save(clienteDTO.map(Cliente.class));
		
		return Optional.ofNullable(clienteSalvo)
			.map(cli -> ClienteDTO.fromEntity(cli));
	}
	
	@Transactional
	public Optional<ClienteDTO> delete(Long id) {		
		return clienteRepository.findById(id)
			.map(cli -> {
				clienteRepository.delete(cli);
				return ClienteDTO.fromEntity(cli);
			});
	}
	
}
