package com.rar.compras.graphql.exception;

public class DomainException extends RuntimeException {

	private static final long serialVersionUID = 8884059689781279884L;

	public DomainException(String message) {
        super(message);
    }

}
