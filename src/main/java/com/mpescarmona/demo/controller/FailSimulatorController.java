package com.mpescarmona.demo.controller;

import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador encargado de pausar la ejecución del servicio de Likes, para simular una falla por tiempo excesivo
 * de respuesta en el circuit breaker.
 */
@Api(value = "Fail simulator controller")
@Slf4j
@Validated
@RestController
@RequestMapping("/fail")
@RequiredArgsConstructor
public class FailSimulatorController {
    private final FailSimulatorService failSimulatorService;

    @ApiOperation("Gets the delay in seconds of mongoDb response to mimic an error and see how circuit breaker behaves")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieves the delay in seconds of mongoDb")})
    @GetMapping(value = "/delay")
    public ResponseEntity<Integer> getDelayInSeconds() {
        return ResponseEntity.ok(failSimulatorService.getDelayInSeconds());
    }

    @ApiOperation("Sets the delay in seconds of mongoDb response to mimic a delayed response with mongoDb and see how circuit breaker behaves")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Sets the delay in seconds of mongoDb response")})
    @PostMapping(value = "/delay",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> setDelayInSeconds(@RequestBody Integer delay) {
        failSimulatorService.setDelayInSeconds(delay);
        return ResponseEntity.ok(delay);
    }
}
