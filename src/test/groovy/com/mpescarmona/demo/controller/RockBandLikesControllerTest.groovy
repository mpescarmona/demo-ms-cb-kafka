package com.mpescarmona.demo.controller

import com.mpescarmona.demo.dto.RockBandDto
import com.mpescarmona.demo.service.rockband.RockBandLikesService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

import java.time.Instant

class RockBandLikesControllerTest extends Specification {
    RockBandLikesService rockBandLikesService
    RockBandLikesController rockBandLikesController

    def setup() {
        rockBandLikesService = Mock(RockBandLikesService)
        rockBandLikesController = new RockBandLikesController(rockBandLikesService)
    }

    def "GetSumOfRockBandLikes"() {
        given:
        def likes = 1500
        rockBandLikesService.getSumOfRockBandLikes() >> likes

        when:
        def result = rockBandLikesController.getSumOfRockBandLikes()

        then:
        result == ResponseEntity.ok(likes)
    }

    def "GetAllRockBandLikes"() {
        given:
        def bands = getAllRockBands()
        rockBandLikesService.getAllRockBandLikes() >> bands

        when:
        def result = rockBandLikesController.getAllRockBandLikes()

        then:
        result == ResponseEntity.ok(bands)
    }

    def "GetTopTenRockBandLikes"() {
        given:
        def bands = getTopTenRockBands()
        rockBandLikesService.getTopTenRockBandLikes() >> bands

        when:
        def result = rockBandLikesController.getTopTenRockBandLikes()

        then:
        result == ResponseEntity.ok(bands)
    }

    def "GetRockBandLikesByName"() {
        given:
        def bands = getLikesByRockBand()
        rockBandLikesService.getLikesByRockBand("Pink Floyd") >> bands

        when:
        def result = rockBandLikesController.getRockBandLikesByName("Pink Floyd")

        then:
        result == ResponseEntity.ok(bands)
    }

    private static getAllRockBands() {
        [
                RockBandDto.builder()
                        .id("001")
                        .rockBand("Pink Floyd")
                        .likes(1000)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("002")
                        .rockBand("AC/DC")
                        .likes(700)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("003")
                        .rockBand("Led Zeppelin")
                        .likes(500)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("004")
                        .rockBand("The Police")
                        .likes(456)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("005")
                        .rockBand("Yes")
                        .likes(432)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("006")
                        .rockBand("The Cure")
                        .likes(321)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("007")
                        .rockBand("Guns 'N Roses")
                        .likes(300)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("008")
                        .rockBand("The Beach Boys")
                        .likes(250)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("009")
                        .rockBand("Bon Jovi")
                        .likes(200)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("010")
                        .rockBand("Stone Temple Pilots")
                        .likes(150)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("011")
                        .rockBand("Coldplay")
                        .likes(100)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build()
        ]
    }

    private static getTopTenRockBands() {
        [
                RockBandDto.builder()
                        .id("001")
                        .rockBand("Pink Floyd")
                        .likes(1000)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("002")
                        .rockBand("AC/DC")
                        .likes(700)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("003")
                        .rockBand("Led Zeppelin")
                        .likes(500)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("004")
                        .rockBand("The Police")
                        .likes(456)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("005")
                        .rockBand("Yes")
                        .likes(432)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("006")
                        .rockBand("The Cure")
                        .likes(321)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("007")
                        .rockBand("Guns 'N Roses")
                        .likes(300)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("008")
                        .rockBand("The Beach Boys")
                        .likes(250)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("009")
                        .rockBand("Bon Jovi")
                        .likes(200)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build(),
                RockBandDto.builder()
                        .id("010")
                        .rockBand("Stone Temple Pilots")
                        .likes(150)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build()
        ]
    }

    private static getLikesByRockBand() {
        [
                RockBandDto.builder()
                        .id("001")
                        .rockBand("Pink Floyd")
                        .likes(1000)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build()
        ]
    }
}