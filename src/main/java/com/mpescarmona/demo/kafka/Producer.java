package com.mpescarmona.demo.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@RequiredArgsConstructor
@EnableBinding(Source.class)
public class Producer {
    private final Source source;
}