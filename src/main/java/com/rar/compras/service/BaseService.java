package com.rar.compras.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import com.rar.compras.service.dto.BaseDTO;

@Service
public class BaseService {

	public <T> Example<T> getExampleFilter (BaseDTO dto, Class<T> entityClass) {
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
					
		Example<T> example = Example.of(dto.map(entityClass), matcher);
		
		return example;
	}
	
}
