package com.rar.compras.graphql.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import graphql.validation.ValidationError;

@Component
public class GraphQLExceptionHandler implements GraphQLErrorHandler {

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
            
        } else if (error instanceof ValidationError) {
            return new SimpleError(error.getMessage());
        }
        return error;
    }
}