package com.rar.compras.graphql.exception;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import graphql.servlet.GenericGraphQLError;

class SimpleError extends GenericGraphQLError {

	private static final long serialVersionUID = 6486401345440719388L;

	SimpleError(String message) {
        super(message);
    }

    @Override
    @JsonIgnore
    public List<Object> getPath() {
        return null;
    }

    @Override
    @JsonIgnore
    public Map<String, Object> getExtensions() {
        return null;
    }
}