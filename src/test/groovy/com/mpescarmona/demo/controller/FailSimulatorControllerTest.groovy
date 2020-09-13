package com.mpescarmona.demo.controller

import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class FailSimulatorControllerTest extends Specification {
    FailSimulatorService failSimulatorService
    FailSimulatorController failSimulatorController

    def setup() {
        failSimulatorService = Mock(FailSimulatorService)
        failSimulatorController = new FailSimulatorController(failSimulatorService)
    }

    def "GetDelayInSeconds"() {
        given:
        failSimulatorService.getDelayInSeconds() >> 5

        when:
        def result = failSimulatorController.delayInSeconds

        then:
        result == ResponseEntity.ok(5)
    }

    def "SetDelayInSeconds"() {
        given:
        def delay = 10
        failSimulatorService.setDelayInSeconds(delay) >> delay

        when:
        def result = failSimulatorController.setDelayInSeconds(delay)

        then:
        result == ResponseEntity.ok(delay)
    }
}
