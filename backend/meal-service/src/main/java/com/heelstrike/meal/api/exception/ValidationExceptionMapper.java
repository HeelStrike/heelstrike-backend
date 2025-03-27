package com.heelstrike.meal.api.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;
import java.util.Map;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    @Override
    public Response toResponse(ConstraintViolationException exception) {
        List<String> messages = exception.getConstraintViolations()
                .stream()
                .map(constraintViolation -> constraintViolation.getPropertyPath() +
                        ": " +
                        constraintViolation.getMessage())
                .toList();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(Map.of("validationErrors", messages))
                .build();
    }
}
