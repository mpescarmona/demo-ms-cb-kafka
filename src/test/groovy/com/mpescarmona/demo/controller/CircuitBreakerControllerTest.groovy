package com.mpescarmona.demo.controller

import com.mpescarmona.demo.service.rockband.RockBandLikesServiceImpl
import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class CircuitBreakerControllerTest extends Specification {
    CircuitBreaker circuitBreaker
    CircuitBreakerRegistry circuitBreakerRegistry
    CircuitBreakerController circuitBreakerController

    def setup() {
        circuitBreaker = Mock(CircuitBreaker)
        circuitBreakerRegistry = Mock(CircuitBreakerRegistry)
        circuitBreakerController = new CircuitBreakerController(circuitBreakerRegistry)
    }

    def "DisableCircuitBreaker"() {
        given:
        circuitBreaker.getState() >> CircuitBreaker.State.DISABLED
        circuitBreakerRegistry.circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE) >> circuitBreaker

        when:
        def result = circuitBreakerController.disableCircuitBreaker()

        then:
        result == ResponseEntity.ok(CircuitBreaker.State.DISABLED)
    }

    def "EnableCircuitBreaker"() {
        given:
        circuitBreaker.getState() >> CircuitBreaker.State.CLOSED
        circuitBreakerRegistry.circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE) >> circuitBreaker

        when:
        def result = circuitBreakerController.enableCircuitBreaker()

        then:
        result == ResponseEntity.ok(CircuitBreaker.State.CLOSED)
    }

    def "GetCircuitBreaker"() {
        given:
        circuitBreaker.getState() >> CircuitBreaker.State.HALF_OPEN
        circuitBreakerRegistry.circuitBreaker(RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE) >> circuitBreaker

        when:
        def result = circuitBreakerController.getCircuitBreaker()

        then:
        result == ResponseEntity.ok(CircuitBreaker.State.HALF_OPEN)
    }
}
