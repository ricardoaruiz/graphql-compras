package com.rar.compras.service.dto;

import org.modelmapper.ModelMapper;

public class BaseDTO {

	private ModelMapper mapper = new ModelMapper();
	
	public <D> D map(Class<D> destinationType) {
		return destinationType != null 
				? mapper.map(this, destinationType)
						: null;
	}
	
}
