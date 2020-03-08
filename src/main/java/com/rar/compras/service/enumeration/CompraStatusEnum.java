package com.rar.compras.service.enumeration;

public enum CompraStatusEnum {

	NOVA("Nova"),
	PENDENTE("Pendente"),
	CONCLUIDA("Concluida");
	
	private String description;

	private CompraStatusEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
}
