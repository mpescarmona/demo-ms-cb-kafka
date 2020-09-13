package com.mpescarmona.demo.controller;

import com.mpescarmona.demo.dto.RockBandDto;
import com.mpescarmona.demo.service.rockband.RockBandLikesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador de likes de bandas de rock.
 */
@Api(value = "Rock band likes controller")
@Slf4j
@Validated
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class RockBandLikesController {
    private final RockBandLikesService rockBandLikesService;

    @ApiOperation("Get the sum of all the rock band likes")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieves the sum of all the rock band likes")})
    @GetMapping(value = "/sum")
    public ResponseEntity<Integer> getSumOfRockBandLikes() {
        return ResponseEntity.ok(rockBandLikesService.getSumOfRockBandLikes());
    }

    @ApiOperation("Get all the rock band likes in descending order")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieve all the rock band likes")})
    @GetMapping(value = "/all")
    public ResponseEntity<List<RockBandDto>> getAllRockBandLikes() {
        return ResponseEntity.ok(rockBandLikesService.getAllRockBandLikes());
    }

    @ApiOperation("Gets the 'Top Ten' of most liked rock bands in descending order")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieve all the rock band likes")})
    @GetMapping(value = "/top10")
    public ResponseEntity<List<RockBandDto>> getTopTenRockBandLikes() {
        return ResponseEntity.ok(rockBandLikesService.getTopTenRockBandLikes());
    }

    @ApiOperation("Get the rock band likes of the given rock band")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieve the list of rock band likes that matches with the given data")})
    @GetMapping(value = "/event")
    public ResponseEntity<List<RockBandDto>> getRockBandLikesByName(@RequestParam String rockBandName) {
        return ResponseEntity.ok(rockBandLikesService.getLikesByRockBand(rockBandName));
    }
}
