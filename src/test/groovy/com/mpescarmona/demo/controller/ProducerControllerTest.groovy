package com.mpescarmona.demo.controller

import com.mpescarmona.demo.service.kafka.KafkaProducerService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class ProducerControllerTest extends Specification {
    ProducerController producerController
    KafkaProducerService kafkaProducerService

    def setup() {
        kafkaProducerService = Mock(KafkaProducerService)
        producerController = new ProducerController(kafkaProducerService)
    }

    def "ProduceRandomRockBandsByNames should return a list of rock bands"() {
        given:
        def bands = [
                "Queen",
                "The Police",
                "Red Hot Chili Peppers",
                "U2",
                "Genesis",
                "The Beatles",
                "Led Zeppelin",
                "Metallica",
                "AC/DC",
                "Pink Floyd"
        ]
        kafkaProducerService.produceRandomRockBandNamesInKafka(bands.size()) >> bands

        when:
        def result = producerController.produceRandomRockBandsByNames(bands.size())

        then:
        result == ResponseEntity.ok(bands)
    }
}
