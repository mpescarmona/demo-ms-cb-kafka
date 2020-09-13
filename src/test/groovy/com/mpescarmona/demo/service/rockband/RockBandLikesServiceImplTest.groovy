package com.mpescarmona.demo.service.rockband

import com.mpescarmona.demo.dto.RockBandDto
import com.mpescarmona.demo.repository.RockBandLikesRepository
import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import spock.lang.Specification

import java.time.Instant

class RockBandLikesServiceImplTest extends Specification {
    RockBandLikesService rockBandLikesService
    RockBandLikesRepository rockBandLikesRepository
    FailSimulatorService failSimulatorService

    def setup() {
        rockBandLikesRepository = Mock(RockBandLikesRepository)
        failSimulatorService = Mock(FailSimulatorService)
        rockBandLikesService = new RockBandLikesServiceImpl(rockBandLikesRepository, failSimulatorService)
    }

    def "GetRockBandLikesCount"() {
        given:
        def count = 1500
        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.count() >> count

        when:
        def result = rockBandLikesService.getRockBandLikesCount()

        then:
        result == count
    }

    def "GetSumOfRockBandLikes"() {
        given:
        def bands = getTopTenRockBands()
        def sum = 4310

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findAll() >> bands

        when:
        def result = rockBandLikesService.getSumOfRockBandLikes()

        then:
        result == sum
    }

    def "GetAllRockBandLikes"() {
        given:
        def bands = getTopTenRockBands()

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findAll(_ as Sort) >> bands

        when:
        def result = rockBandLikesService.getAllRockBandLikes()

        then:
        result == bands
    }

    def "GetTopTenRockBandLikes"() {
        given:
        def topTen = getTopTenRockBands()

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findAll(_ as Pageable) >> new PageImpl(topTen)

        when:
        def result = rockBandLikesService.getTopTenRockBandLikes()

        then:
        result == topTen
    }

    def "GetLikesByRockBand"() {
        given:
        def band = "Pink Floyd"
        def bandLikes = getLikesByRockBand(band)

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findByRockBandAllIgnoreCase(_ as String) >> Optional.of(bandLikes)

        when:
        def result = rockBandLikesService.getLikesByRockBand(band)

        then:
        result == bandLikes
    }

    def "GetLikesByRockBand should return empty list when the band was not found"() {
        given:
        def bandLikes = []

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findByRockBandAllIgnoreCase(_ as String) >> Optional.of(bandLikes)

        when:
        def result = rockBandLikesService.getLikesByRockBand("Unknown band")

        then:
        result == bandLikes
    }

    def "CreateOrUpdateLikesByRockBand should create a new rock band"() {
        given:
        def band = "Pink Floyd"
        def newBand = getNewRockBand(band)

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findByRockBandAllIgnoreCase(band) >> Optional.empty()

        when:
        def result = rockBandLikesService.createOrUpdateLikesByRockBand(band)

        then:
        1 * rockBandLikesRepository.insert(_ as RockBandDto) >> newBand
        0 * rockBandLikesRepository.save(_ as RockBandDto)
        result == newBand
    }

    def "CreateOrUpdateLikesByRockBand should update likes of an existing rock band"() {
        given:
        def band = "Pink Floyd"
        def existingBand = getNewRockBand(band)

        failSimulatorService.getDelayInSeconds() >> 0
        rockBandLikesRepository.findByRockBandAllIgnoreCase(band) >> Optional.of([existingBand])

        when:
        def result = rockBandLikesService.createOrUpdateLikesByRockBand(band)

        then:
        0 * rockBandLikesRepository.insert(_ as RockBandDto)
        1 * rockBandLikesRepository.save(_ as RockBandDto) >> existingBand
        result.id == existingBand.id
        result.rockBand == band
        result.likes == 2
        result.created == existingBand.created
        result.updated != null
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
                        .likes(453)
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
                        .likes(304)
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

    private static getLikesByRockBand(String rockBand) {
        [
                RockBandDto.builder()
                        .id("001")
                        .rockBand(rockBand)
                        .likes(1000)
                        .created(Instant.now())
                        .updated(Instant.now())
                        .build()
        ]
    }

    private static getNewRockBand(String rockBand) {
        RockBandDto.builder()
                .id("001")
                .rockBand(rockBand)
                .likes(1)
                .created(Instant.now())
                .build()
    }
}
