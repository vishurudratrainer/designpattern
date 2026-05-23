package com.enterprise.resilience;

import com.enterprise.resilience.service.RegionalLogisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequiredArgsConstructor
public class ResilientApplication {

    private final RegionalLogisticsService logisticsService;

    /**
     * Standard main startup hook for Spring Boot deployment runtimes.
     */
    public static void main(String[] args) {
        SpringApplication.run(ResilientApplication.class, args);
    }

    /**
     * Edge Endpoint to simulate real-world production conditions.
     *
     * @param tenantId The unique user space or corporate workspace account identifier
     * @param region   The execution environment location (e.g., us-east, ap-south, eu-central)
     * @param mode     Controls execution flow simulation behavior ("SUCCESS", "FAIL_NET", or "FAIL_VALIDATION")
     */
    @GetMapping("/api/v1/fulfillment")
    public String triggerFulfillment(
            @RequestParam String tenantId,
            @RequestParam String region,
            @RequestParam(required = false, defaultValue = "SUCCESS") String mode) {

        return logisticsService.processFulfillment(tenantId, region, mode);
    }
}