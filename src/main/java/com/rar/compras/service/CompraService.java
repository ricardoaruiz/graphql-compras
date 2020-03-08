package com.rar.compras.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rar.compras.model.entity.Compra;
import com.rar.compras.model.repository.ClienteRepository;
import com.rar.compras.model.repository.CompraRepository;
import com.rar.compras.service.dto.CompraDTO;
import com.rar.compras.service.enumeration.CompraStatusEnum;

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
	public Optional<CompraDTO> criarCompra(CompraDTO compra) {
		compra.setStatus(CompraStatusEnum.NOVA.getDescription());
		return Optional.ofNullable(compraRepository.save(compra.map(Compra.class)))
			.map(cpr -> CompraDTO.fromEntity(cpr));
	}
	
	@Transactional
	public Optional<CompraDTO> removerCompra(Long id) {
		return compraRepository.findById(id)
			.map(cpr -> {
				compraRepository.delete(cpr);
				return CompraDTO.fromEntity(cpr);
			});
	}
	
}
