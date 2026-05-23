package com.enterprise.resilience;

import com.enterprise.resilience.service.RegionalLogisticsService;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResilientApplicationTest {

    @Autowired
    private RegionalLogisticsService logisticsService;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

    @BeforeEach
    void clearRegistryState() {
        // Clear runtime state before every execution run to keep memory profiles clean
        circuitBreakerRegistry.getAllCircuitBreakers().forEach(cb -> circuitBreakerRegistry.remove(cb.getName()));
    }

    @Test
    @DisplayName("Normal Behavior: System should return raw success strings when network parameters are stable")
    void verifyNormalProcessingPath() {
        // Act
        String result = logisticsService.processFulfillment("tenant-alpha", "us-east", "SUCCESS");

        // Assert
        assertTrue(result.contains("SUCCESS"), "Expected successful primary network payload handling.");
        assertFalse(result.contains("DEGRADED"), "Fallback mechanics should not execute during nominal paths.");

        // Confirm dynamic memory registry mapping works out of the box
        CircuitBreaker generatedBreaker = circuitBreakerRegistry.circuitBreaker("cb_tenant-alpha_us-east");
        assertNotNull(generatedBreaker);
        assertEquals(CircuitBreaker.State.CLOSED, generatedBreaker.getState(), "Circuit state must start CLOSED.");
    }

    @Test
    @DisplayName("Switchover Behavior: Repeated network degradation must force transition to fallback execution routes")
    void verifySwitchoverAndStateTripMechanisms() {
        String tenant = "standard-customer";
        String region = "eu-west";
        String breakerKey = "cb_standard-customer_eu-west";

        // Step 1: Force successive transport layer drops down the pipeline.
        // Our configuration minimum window requires 5 distinct operations before tripping.
        for (int i = 0; i < 5; i++) {
            String recoveryResponse = logisticsService.processFulfillment(tenant, region, "FAIL_NET");

            // Confirm the system remains functional by switching over smoothly to degraded caches
            assertTrue(recoveryResponse.contains("DEGRADED"), "Should serve fallback gracefully while dropping connections.");
            assertTrue(recoveryResponse.contains("fallback engine edge cache"), "Verifying output source structure match.");
        }

        // Step 2: Extract the dynamically provisioned state-machine block
        CircuitBreaker targetedBreaker = circuitBreakerRegistry.circuitBreaker(breakerKey);

        // Assert: The circuit breaker should now be wide open due to the failure rate crossing the 40% threshold
        assertEquals(CircuitBreaker.State.OPEN, targetedBreaker.getState(),
                "The circuit breaker should be tripped OPEN after hitting failure parameters.");

        // Step 3: Fast Check. Fire an immediate nominal request while the ring is open.
        // It must drop straight into fallback instantly without even trying to evaluate the "SUCCESS" string network lambda.
        String immediateFallback = logisticsService.processFulfillment(tenant, region, "SUCCESS");
        assertTrue(immediateFallback.contains("DEGRADED"),
                "Fast-fail path must short-circuit requests directly to cached limits while circuit is open.");
    }

    @Test
    @DisplayName("Isolation Context Protection: Tripping a specific user infrastructure zone must leave unrelated tenants intact")
    void verifyBlastRadiusContainment() {
        // Step 1: Deliberately break the path for 'Tenant-A' inside 'us-east'
        for (int i = 0; i < 6; i++) {
            logisticsService.processFulfillment("tenant-a", "us-east", "FAIL_NET");
        }

        // Validate Tenant-A context state
        CircuitBreaker breakerA = circuitBreakerRegistry.circuitBreaker("cb_tenant-a_us-east");
        assertEquals(CircuitBreaker.State.OPEN, breakerA.getState(), "Tenant A must blow its structural fuse.");

        // Step 2: Execute an unrelated lookup for 'Tenant-B' over the identical shared computing environment
        String cleanResultTenantB = logisticsService.processFulfillment("tenant-b", "us-east", "SUCCESS");

        // Assert: Tenant B's experience is completely normal and unimpacted by Tenant A's problems
        assertTrue(cleanResultTenantB.contains("SUCCESS"), "Tenant B should completely bypass neighbors' localized problems.");

        CircuitBreaker breakerB = circuitBreakerRegistry.circuitBreaker("cb_tenant-b_us-east");
        assertEquals(CircuitBreaker.State.CLOSED, breakerB.getState(), "Tenant B's circuit ring must remain healthy and CLOSED.");
    }

    @Test
    @DisplayName("Exception Exclusions: Logical bad validation requests should not alter system state metrics")
    void verifyBusinessValidationErrorsAreIgnoredByBreakerTopology() {
        String tenant = "standard-validation-user";
        String region = "us-west";

        // Step 1: Run successive invalid payloads down the processor
        for (int i = 0; i < 6; i++) {
            logisticsService.processFulfillment(tenant, region, "FAIL_VALIDATION");
        }

        // Step 2: Grab context instance metrics
        CircuitBreaker validationBreaker = circuitBreakerRegistry.circuitBreaker("cb_standard-validation-user_us-west");

        // Assert: Even though there were exceptions, the state breaker must remain CLOSED.
        // Business data bugs are not infrastructural drops; they are handled safely outside topology calculations.
        assertEquals(CircuitBreaker.State.CLOSED, validationBreaker.getState(),
                "The circuit must stay CLOSED because validation errors are marked as 'ignoreExceptions'.");
    }
}