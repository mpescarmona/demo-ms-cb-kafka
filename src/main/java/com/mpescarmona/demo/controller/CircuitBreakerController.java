package com.mpescarmona.demo.controller;

import com.mpescarmona.demo.service.rockband.RockBandLikesServiceImpl;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que expone el estado del microservicio
 */
@Api(value = "CircuitBreaker State controller")
@Slf4j
@Validated
@RestController
@RequestMapping("/circuit-breaker")
@RequiredArgsConstructor
public class CircuitBreakerController {
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @ApiOperation("Disables the circuit breaker")
    @PostMapping(value = "/disable")
    public ResponseEntity<CircuitBreaker.State> disableCircuitBreaker() {
        circuitBreakerRegistry
                .circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE)
                .transitionToDisabledState();

        return getCiscuitBreakerStatus();
    }

    @ApiOperation("Sets the circuit breaker to CLOSED status")
    @PostMapping(value = "/close")
    public ResponseEntity<CircuitBreaker.State> enableCircuitBreaker() {
        circuitBreakerRegistry
                .circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE)
                .transitionToClosedState();

        return getCiscuitBreakerStatus();
    }

    @ApiOperation("Gets the circuit breaker status")
    @GetMapping(value = "/status")
    public ResponseEntity<CircuitBreaker.State> getCircuitBreaker() {

        return getCiscuitBreakerStatus();
    }

    private ResponseEntity<CircuitBreaker.State> getCiscuitBreakerStatus() {
        return ResponseEntity.ok(circuitBreakerRegistry
                .circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE)
                .getState());
    }
}
