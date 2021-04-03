package com.mpescarmona.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StatusDto {
    Long bandsRated;
    List<RockBandDto> topTen;
    Integer delay;
}
