package com.mpescarmona.demo.service.failsimulatormongo

import spock.lang.Specification

import java.time.LocalTime

class FailSimulatorServiceImplTest extends Specification {
    def failSimulatorService
    def list

    def setup() {
        list = Mock(List)
        failSimulatorService = new FailSimulatorServiceImpl()
    }

    def "delayExecution"() {
        given:
        def delay = 3
        failSimulatorService.setDelayInSeconds(delay)

        when:
        def t1 = LocalTime.now().toSecondOfDay()
        delayedMethod()
        def t2 = LocalTime.now().toSecondOfDay()

        then:
        1 * list.add(_)
        t2 - t1 == delay
    }

    private void delayedMethod() {
        failSimulatorService.delayExecution()
        list.add("")
    }
}
