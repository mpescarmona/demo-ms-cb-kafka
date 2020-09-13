package com.mpescarmona.demo.service.failsimulatormongo;

public interface FailSimulatorService {
    Integer getDelayInSeconds();
    Integer setDelayInSeconds(Integer delay);
    void delayExecution();
}
