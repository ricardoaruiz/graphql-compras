package com.rar.compras.graphql.resolvers;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

@Component
public class BaseQueryResolver implements GraphQLQueryResolver {
	
	public String hello() {
		return "Hello GraphQL";
	}
	
}
