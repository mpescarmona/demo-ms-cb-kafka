package com.mpescarmona.demo.controller;

import com.mpescarmona.demo.service.kafka.KafkaProducerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador encargado de producir eventos de Bandas de rock en kafka.
 */
@Api(value = "Rock band likes controller")
@Slf4j
@Validated
@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class ProducerController {
    private final KafkaProducerService kafkaProducerService;

    @ApiOperation("Produces the given quantity of rock band names in kafka")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Produces the given quantity of rock bands in kafka")})
    @PostMapping(value = "/produce",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> produceRandomRockBandsByNames(@RequestBody Integer quantity) {
        return ResponseEntity.ok(kafkaProducerService.produceRandomRockBandNamesInKafka(quantity));
    }
}
