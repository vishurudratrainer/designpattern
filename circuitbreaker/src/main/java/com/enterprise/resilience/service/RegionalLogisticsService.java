package com.enterprise.resilience.service;

import com.enterprise.resilience.breaker.ResilientExecutionManager;
import com.enterprise.resilience.exception.BusinessValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionalLogisticsService {

    private final ResilientExecutionManager resilienceManager;

    /**
     * Orchestrates delivery route processing inside the protective isolation wrapper.
     */
    public String processFulfillment(String tenantId, String region, String simulationMode) {
        return resilienceManager.executeProtectedCall(
                tenantId,
                region,
                // Lambda block representing the primary outbound transaction stream
                () -> simulateRemoteNetworkCall(simulationMode),
                // Lambda block representing the local failover mechanism executed if the primary stream fails or is blocked
                () -> executeDegradedFallback(tenantId, region)
        );
    }

    /**
     * Simulates real-world remote processing behavior based on incoming test requests.
     */
    private String simulateRemoteNetworkCall(String mode) {
        // Scenario A: Simulates a hard network timeout (e.g., TCP connection dropped or target API down)
        if ("FAIL_NET".equalsIgnoreCase(mode)) {
            throw new RuntimeException("Network pipe timed out drop frame - Socket Exception");
        }

        // Scenario B: Simulates bad payload structures or user input validation errors
        if ("FAIL_VALIDATION".equalsIgnoreCase(mode)) {
            throw new BusinessValidationException("Invalid structural parameters - 400 Bad Request Payload");
        }

        // Default Path: Healthy downstream processing response
        return "{\"status\":\"SUCCESS\",\"payload\":\"Data delivered securely through primary network channel.\"}";
    }

    /**
     * Graceful Fallback Strategy.
     * In production, this can pull data from a local read-only database replica or an edge distributed cache
     * instead of returning a generic error page to the end-user.
     */
    private String executeDegradedFallback(String tenantId, String region) {
        log.warn("Executing fallback data route for Tenant: {}, Region: {}", tenantId, region);
        return String.format("{\"status\":\"DEGRADED\",\"tenant\":\"%s\",\"region\":\"%s\",\"msg\":\"Served from fallback engine edge cache.\"}",
                tenantId, region);
    }
}