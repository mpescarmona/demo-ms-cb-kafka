package com.mpescarmona.demo.service.kafka

import com.github.javafaker.Faker
import com.mpescarmona.demo.kafka.Producer
import org.springframework.cloud.stream.messaging.Source
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import spock.lang.Specification

class KafkaProducerServiceImplTest extends Specification {
    Producer producer
    Source source
    MessageChannel messageChannel
    Faker faker
    KafkaProducerService kafkaProducerService

    def setup() {
        source = Mock(Source)
        producer = Mock(Producer)
        messageChannel = Mock(MessageChannel)
        faker = new Faker()
        kafkaProducerService = new KafkaProducerServiceImpl(producer, faker)
    }

    def "ProduceRandomRockBandNamesInKafka"() {
        given:
        def quantity = 1000
        producer.getSource() >> source
        producer.getSource().output() >> messageChannel
        producer.getSource().output().send(_ as Message) >> true

        when:
        def result = kafkaProducerService.produceRandomRockBandNamesInKafka(quantity)

        then:
        result != null
        result.size() == quantity
        quantity * producer.getSource().output().send(_ as Message)
    }
}
