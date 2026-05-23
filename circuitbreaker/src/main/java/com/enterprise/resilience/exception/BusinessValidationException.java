package com.enterprise.resilience.exception;

/**
 * Enterprise Guardrail Exception.
 * Represents a failure caused by client-side data input issues (e.g., a 400 Bad Request payload).
 *
 * Crucial Design Point: This class must extend RuntimeException but be ignored by the
 * circuit breaker engine. If bad user data trips your circuit breakers, malicious or clumsy
 * users could trigger a denial-of-service (DoS) condition against your downstream microservices.
 */
public class BusinessValidationException extends RuntimeException {
    public BusinessValidationException(String message) {
        super(message);
    }
}