package com.mpescarmona.demo.kafka;

import com.mpescarmona.demo.dto.RockBandDto;
import com.mpescarmona.demo.service.rockband.RockBandLikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

/*
 * https://dzone.com/articles/kafka-with-spring-cloud-stream
 * https://github.com/nakulshukla08/techwording
 */
@Slf4j
@Component
@RequiredArgsConstructor
@EnableBinding(Sink.class)
public class Consumer {
    private final RockBandLikesService rockBandLikesService;

    @StreamListener(target = Sink.INPUT, condition = "headers['type']=='rockBand'")
    public void consumeRockBand(String rockbandName) {
        log.info("Received a rock band as string: " + rockbandName);

        RockBandDto rockBand = rockBandLikesService.createOrUpdateLikesByRockBand(rockbandName);
        log.info("rockBand = " + rockBand);
    }
}