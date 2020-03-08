package com.rar.compras.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rar.compras.model.entity.Cliente;
import com.rar.compras.model.entity.Compra;

public interface CompraRepository extends JpaRepository<Compra, Long> {

	public List<Compra> findAllByCliente(Cliente cliente);
	
}
