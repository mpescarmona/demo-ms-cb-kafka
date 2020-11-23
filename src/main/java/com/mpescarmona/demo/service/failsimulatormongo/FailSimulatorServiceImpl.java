package com.mpescarmona.demo.service.failsimulatormongo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FailSimulatorServiceImpl implements FailSimulatorService {
    private Integer delay = 0;

    @Override
    public Integer getDelayInSeconds() {
        return this.delay;
    }

    @Override
    public Integer setDelayInSeconds(Integer delay) {
        this.delay = delay;
        return delay;
    }

    @Override
    public void delayExecution() {
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
