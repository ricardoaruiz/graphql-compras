package com.rar.compras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rar.compras.graphql.exception.DomainException;
import com.rar.compras.model.entity.Compra;
import com.rar.compras.model.repository.ClienteRepository;
import com.rar.compras.model.repository.CompraRepository;
import com.rar.compras.service.dto.CompraDTO;

@Service
public class CompraService {

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Optional<CompraDTO> findById(Long id) {
		return compraRepository.findById(id)
				.map(cpr -> CompraDTO.fromEntity(cpr));
	}
	
	@Transactional(readOnly = true)
	public List<CompraDTO> findAll(CompraDTO compra) {
		
		Example<Compra> filtro = baseService.getExampleFilter(compra, Compra.class);
		
		return compraRepository.findAll(filtro)
			.stream()
			.map(cpr -> CompraDTO.fromEntity(cpr))
			.collect(Collectors.toList());
	}
	
	@Cacheable(value="comprasByCliente", key="#clienteId")
	@Transactional(readOnly = true)
	public List<CompraDTO> findAllByClienteId(Long clienteId) {		
		return clienteRepository.findById(clienteId)
			.map(cliente -> compraRepository.findAllByCliente(cliente)
					.stream()
					.map(compra -> CompraDTO.fromEntity(compra))
					.collect(Collectors.toList())					
			)
			.orElse(new ArrayList<CompraDTO>());
			
	}
	
	@Transactional
	@Caching(evict = {
		@CacheEvict(value="comprasByCliente", key="#compra.clienteId"),
		@CacheEvict(value="clientes", key="#compra.clienteId")
	})
	public Optional<CompraDTO> criarCompra(CompraDTO compra) {
		
		if(compra.getQuantidade() > 100) {
			throw new DomainException("Quantidade máxima de itens por pedido excedida");
		}
					
		return Optional.ofNullable(compraRepository.save(compra.map(Compra.class)))
		.map(cpr -> CompraDTO.fromEntity(cpr));		
	}
	
	@Transactional
	@Caching(evict = {
			@CacheEvict(value="comprasByCliente", allEntries = true)
	})
	public Optional<CompraDTO> removerCompra(Long compraId) {
		return compraRepository.findById(compraId)
			.map(cpr -> {
				compraRepository.delete(cpr);
				return CompraDTO.fromEntity(cpr);
			});
	}
	
}
