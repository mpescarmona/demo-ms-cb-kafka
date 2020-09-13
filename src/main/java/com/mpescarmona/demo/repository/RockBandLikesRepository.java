package com.mpescarmona.demo.repository;

import com.mpescarmona.demo.dto.RockBandDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RockBandLikesRepository extends MongoRepository<RockBandDto, String> {
    Optional<List<RockBandDto>> findByRockBandAllIgnoreCase(String rockBand);
}
