package com.mpescarmona.demo.controller;

import com.mpescarmona.demo.dto.StatusDto;
import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService;
import com.mpescarmona.demo.service.rockband.RockBandLikesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador que expone el estado del microservicio
 */
@Api(value = "Status controller")
@Slf4j
@Validated
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class StatusController {
    private final RockBandLikesService rockBandLikesService;
    private final FailSimulatorService failSimulatorService;

    @ApiOperation("Gets the microservice status")
    @GetMapping(value = "/status")
    public ResponseEntity<StatusDto> getStatus() {

        StatusDto statusDto = StatusDto.builder()
                .bandsRated(rockBandLikesService.getRockBandLikesCount())
                .topTen(rockBandLikesService.getTopTenRockBandLikes())
                .delay(failSimulatorService.getDelayInSeconds())
                .build();
        return ResponseEntity.ok(statusDto);
    }
}
