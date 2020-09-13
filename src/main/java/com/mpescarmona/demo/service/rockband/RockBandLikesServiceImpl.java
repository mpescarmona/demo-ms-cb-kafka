package com.mpescarmona.demo.service.rockband;

import com.mpescarmona.demo.dto.RockBandDto;
import com.mpescarmona.demo.repository.RockBandLikesRepository;
import com.mpescarmona.demo.service.failsimulatormongo.FailSimulatorService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@CircuitBreaker(name = RockBandLikesServiceImpl.MONGO_DB_CIRCUIT_BREAKER_INSTANCE)
public class RockBandLikesServiceImpl implements RockBandLikesService {
    public static final String MONGO_DB_CIRCUIT_BREAKER_INSTANCE = "mongoDbCircuitBreakerInstance";
    private final RockBandLikesRepository rockBandLikesRepository;
    private final FailSimulatorService failSimulatorService;

    @Override
    public Long getRockBandLikesCount() {
        this.delayResponse();
        return rockBandLikesRepository.count();
    }

    @Override
    public Integer getSumOfRockBandLikes() {
        this.delayResponse();
        return rockBandLikesRepository.findAll()
                .stream()
                .mapToInt(RockBandDto::getLikes)
                .sum();
    }

    @Override
    public List<RockBandDto> getAllRockBandLikes() {
        this.delayResponse();
        return rockBandLikesRepository.findAll(Sort.by(Sort.Direction.DESC, "likes"));
    }

    @Override
    public List<RockBandDto> getTopTenRockBandLikes() {
        this.delayResponse();
        Page<RockBandDto> result = rockBandLikesRepository.findAll(
                PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "likes")));

        return result.getContent();
    }

    @Override
    public List<RockBandDto> getLikesByRockBand(String rockBandName) {
        this.delayResponse();
        return rockBandLikesRepository.findByRockBandAllIgnoreCase(rockBandName)
                .orElse(Collections.emptyList());
    }

    @Override
    public RockBandDto createOrUpdateLikesByRockBand(String rockBandName) {
        RockBandDto result;

        this.delayResponse();

        Optional<List<RockBandDto>> foundLikes = rockBandLikesRepository.findByRockBandAllIgnoreCase(rockBandName);
        if (foundLikes.isPresent() && !foundLikes.get().isEmpty()) {
            RockBandDto firstFoundLike = foundLikes.get().get(0);
            firstFoundLike.setLikes(firstFoundLike.getLikes() + 1);
            firstFoundLike.setUpdated(Instant.now());

            result = rockBandLikesRepository.save(firstFoundLike);
        } else {
            result = rockBandLikesRepository.insert(createNewLike(rockBandName));
        }

        return result;
    }

    private RockBandDto createNewLike(String rockBand) {
        return RockBandDto.builder()
                .rockBand(rockBand)
                .likes(1)
                .created(Instant.now())
                .build();
    }

    private void delayResponse() {
        if (failSimulatorService.getDelayInSeconds() > 0) {
            failSimulatorService.delayExecution();
        }
    }
}
