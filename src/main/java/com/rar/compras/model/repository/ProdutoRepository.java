package com.rar.compras.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rar.compras.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
