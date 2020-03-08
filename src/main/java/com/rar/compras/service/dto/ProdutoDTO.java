package com.rar.compras.service.dto;

import java.math.BigDecimal;

import com.rar.compras.model.entity.Produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ProdutoDTO extends BaseDTO {

	private Long id;
	private String nome;
	private BigDecimal valor;
	
	public static ProdutoDTO fromEntity(Produto produto) {
		return new ProdutoDTO(
				produto.getId(), 
				produto.getNome(), 
				produto.getValor());
	}
	
}
