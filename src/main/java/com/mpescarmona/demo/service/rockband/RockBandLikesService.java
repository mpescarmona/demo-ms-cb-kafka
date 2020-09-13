package com.mpescarmona.demo.service.rockband;

import com.mpescarmona.demo.dto.RockBandDto;

import java.util.List;

public interface RockBandLikesService {
    Long getRockBandLikesCount();
    Integer getSumOfRockBandLikes();
    List<RockBandDto> getAllRockBandLikes();
    List<RockBandDto> getTopTenRockBandLikes();
    List<RockBandDto> getLikesByRockBand(String rockBandName);
    RockBandDto createOrUpdateLikesByRockBand(String rockBandName);
}
