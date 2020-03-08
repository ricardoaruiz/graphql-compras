package com.rar.compras.service.dto;

import java.util.Date;

import com.rar.compras.graphql.resolvers.cliente.domain.ClienteType;
import com.rar.compras.graphql.resolvers.produto.domain.ProdutoType;
import com.rar.compras.model.entity.Compra;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CompraDTO extends BaseDTO {

	private Long id;
	private Date data;
	private Integer quantidade;
	private String status;
	private Long clienteId;
	private Long produtoId;

	public CompraDTO(Compra compra) {
		super();
		this.id = compra.getId();
		this.data = compra.getData();
		this.quantidade = compra.getQuantidade();
		this.status = compra.getStatus();
		this.clienteId = compra.getCliente().getId();
		this.produtoId = compra.getProduto().getId();
	}
		
	public static CompraDTO fromEntity(Compra compra) {
		return new CompraDTO(compra);		
	}
	
}
