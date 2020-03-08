package com.rar.compras.graphql.domain;

import org.modelmapper.ModelMapper;

public class BaseType {

	private ModelMapper mapper = new ModelMapper();
	
	public <D> D map(Class<D> destinationType) {
		return destinationType != null 
				? mapper.map(this, destinationType)
						: null;
	}
	
}
