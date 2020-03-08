package com.rar.compras.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rar.compras.model.entity.Produto;
import com.rar.compras.model.repository.ProdutoRepository;
import com.rar.compras.service.dto.ProdutoDTO;

@Service
public class ProdutoService {

	@Autowired
	private BaseService baseService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional(readOnly = true)
	public Optional<ProdutoDTO> findById(Long id) {
		return produtoRepository.findById(id)
			.map(prd -> ProdutoDTO.fromEntity(prd));
	}
	
	@Transactional(readOnly = true)
	public List<ProdutoDTO> findAll(ProdutoDTO produtoDTO) {
				
		Example<Produto> filter = baseService.getExampleFilter(produtoDTO, Produto.class);
		
		return produtoRepository.findAll(filter)
			.stream()
			.map(prd -> ProdutoDTO.fromEntity(prd))
			.collect(Collectors.toList());		
	}
	
	@Transactional
	public Optional<ProdutoDTO> save(ProdutoDTO produtoDTO) {
		Produto produtoSalvo = produtoRepository.save(produtoDTO.map(Produto.class));
		
		return Optional.ofNullable(produtoSalvo)
			.map(prd -> ProdutoDTO.fromEntity(prd));			
	}
	
	@Transactional
	public Optional<ProdutoDTO> delete(Long id) {
		return produtoRepository.findById(id)
			.map(prd -> {
				produtoRepository.delete(prd);
				return ProdutoDTO.fromEntity(prd);
			});
	}
	
}
