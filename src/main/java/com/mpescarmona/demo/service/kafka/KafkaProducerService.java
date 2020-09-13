package com.mpescarmona.demo.service.kafka;

import java.util.List;

public interface KafkaProducerService {
    List<String> produceRandomRockBandNamesInKafka(Integer quantity);
}
