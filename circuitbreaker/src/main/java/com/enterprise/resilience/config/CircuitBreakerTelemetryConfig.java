package com.enterprise.resilience.config;


import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class CircuitBreakerTelemetryConfig {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public CircuitBreakerTelemetryConfig(CircuitBreakerRegistry circuitBreakerRegistry) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
    }

    /**
     * Binds real-time telemetry log consumers to all circuit breakers.
     * Because breakers are initialized dynamically at runtime, this centralized hook acts as a listener
     * that automatically attaches tracking logic to new instances as they are spun up.
     */
    @PostConstruct
    public void linkGlobalTelemetry() {
        // Event listener triggered whenever a new tenant/region bulkhead combination enters the tracking memory registry
        circuitBreakerRegistry.getEventPublisher().onEntryAdded(addedEntryEvent -> {
            var instantiatedBreaker = addedEntryEvent.getAddedEntry();
            log.info("Telemetry Engine bound to new dynamic context node: {}", instantiatedBreaker.getName());

            // Bind loggers directly onto the circuit breaker event publisher ring buffer
            instantiatedBreaker.getEventPublisher()
                    .onStateTransition(stateEvent ->
                            // CRITICAL ALERT: Logs when an entire tenant/region cell trips open, allowing immediate alerting via log collectors (e.g., Datadog, Splunk)
                            log.error("SYSTEM_ALERT: Isolation Ring [ {} ] changed state from {} to {}",
                                    stateEvent.getCircuitBreakerName(),
                                    stateEvent.getStateTransition().getFromState(),
                                    stateEvent.getStateTransition().getToState()
                            )
                    )
                    .onError(errEvent ->
                            // PERFORMANCE TRACKING: Monitors execution latency to track microservice degradation profiles
                            log.warn("METRIC_CAPTURE: Context [ {} ] error registered. Duration: {} ms",
                                    errEvent.getCircuitBreakerName(),
                                    errEvent.getElapsedDuration().toMillis()
                            )
                    );
        });
    }
}
