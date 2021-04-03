package com.mpescarmona.demo.controller

import com.mpescarmona.demo.dto.StatusDto
import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService
import com.mpescarmona.demo.service.rockband.RockBandLikesService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class StatusControllerTest extends Specification {
    RockBandLikesService rockBandLikesService
    FailSimulatorService failSimulatorService
    StatusController statusController

    def setup() {
        rockBandLikesService = Mock(RockBandLikesService)
        failSimulatorService = Mock(FailSimulatorService)
        statusController = new StatusController(rockBandLikesService, failSimulatorService)
    }

    def "GetStatus should return the status of rock band and fail services"() {
        given:
        def delay = 0
        def likes = 1500
        def expectedResult = StatusDto.builder()
                .delay(delay)
                .bandsRated(likes)
                .build()
        rockBandLikesService.getRockBandLikesCount() >> likes
        failSimulatorService.getDelayInSeconds() >> delay

        when:
        def result = statusController.getStatus()

        then:
        result == ResponseEntity.ok(expectedResult)
    }
}
