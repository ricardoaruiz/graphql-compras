package com.rar.compras.graphql.exception;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import graphql.validation.ValidationError;

@Component
public class GraphQLExceptionHandler implements GraphQLErrorHandler {

	@Autowired
	private Environment env;
	
    @Override
    public List<GraphQLError> processErrors(List<GraphQLError> list) {
        return list.stream()
        		.map(this::getErros)
        		.collect(Collectors.toList());
    }

    private GraphQLError getErros(GraphQLError error) {
        if (error instanceof ExceptionWhileDataFetching) {
            ExceptionWhileDataFetching exceptionError = (ExceptionWhileDataFetching) error;
            
            if (exceptionError.getException() instanceof DomainException) {
                return new SimpleError(
                		exceptionError.getException().getMessage());
            }
            
            boolean prod = !Stream.of( env.getActiveProfiles())
            		.filter(e -> e.equals("dev") || e.equals("test"))
            		.findFirst().isPresent();
            
            if (!prod) {
            	return new SimpleError(
            			"Ocorreu um erro ao processar a transação", 
            			((ExceptionWhileDataFetching) error).getException());
            }            	
            	
            return new SimpleError("Ocorreu um erro ao processar a transação");
            
        } else if (error instanceof ValidationError) {
            return new SimpleError(error.getMessage());
        }
        return error;
    }
}