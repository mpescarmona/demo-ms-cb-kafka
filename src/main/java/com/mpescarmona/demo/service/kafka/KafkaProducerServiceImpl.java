package com.mpescarmona.demo.service.kafka;

import com.github.javafaker.Faker;
import com.mpescarmona.demo.kafka.Producer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final Producer producer;
    private final Faker faker;

    @Override
    public List<String> produceRandomRockBandNamesInKafka(Integer quantity) {
        List<String> rockBands = new ArrayList<>();
        IntStream.rangeClosed(1, quantity)
                .forEach(i -> rockBands.add(produceRandomRockBandInKafka()));
        return rockBands;
    }

    private String produceRandomRockBandInKafka() {
        String band = faker.rockBand().name();
        produceDataInKafka(band, "rockBand");
        return band;
    }

    private <T> void produceDataInKafka(T data, String type) {
        log.info("data = " + data + ", type = " + type);
        producer.getSource()
                .output()
                .send(MessageBuilder
                        .withPayload(data)
                        .setHeader("type", type)
                        .build()
                );
    }
}
