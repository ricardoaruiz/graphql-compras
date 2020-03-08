package com.rar.compras.config;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rar.compras.model.entity.Cliente;
import com.rar.compras.model.entity.Compra;
import com.rar.compras.model.entity.Produto;
import com.rar.compras.model.repository.ClienteRepository;
import com.rar.compras.model.repository.CompraRepository;
import com.rar.compras.model.repository.ProdutoRepository;

@Component
public class InitialConfig {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Bean
	public void init() {
		
		Cliente cliente1 = new Cliente("Ricardo", "ricardo@email.com");
		Cliente cliente2 = new Cliente("Cinthya", "cinthya@email.com");
		clienteRepository.saveAll(Arrays.asList(cliente1, cliente2));
		
		Produto produto1 = new Produto("Produto 01", BigDecimal.valueOf(15.5));
		Produto produto2 = new Produto("Produto 02", BigDecimal.valueOf(19.9));
		produtoRepository.saveAll(Arrays.asList(produto1, produto2));
		
		Compra compra1Cliente1 = new Compra(new Date(), 1, "Pago", cliente1, produto1);
		Compra compra2Cliente1 = new Compra(new Date(), 2, "Pendente", cliente1, produto2);
		Compra compra1Cliente2 = new Compra(new Date(), 4, "Pago", cliente2, produto1);
		Compra compra2Cliente2 = new Compra(new Date(), 3, "Pendente", cliente2, produto2);
		compraRepository.saveAll(Arrays.asList(compra1Cliente1, compra1Cliente2, compra2Cliente1, compra2Cliente2));
		
	}
	
}
