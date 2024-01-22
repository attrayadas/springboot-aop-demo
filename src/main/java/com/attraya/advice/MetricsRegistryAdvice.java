package com.attraya.advice;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsRegistryAdvice {

    @Autowired
    private ObservationRegistry observationRegistry;

    /* Implementing ObservationRegistry feature of Spring Boot 3:
    * 1. Go to http://localhost:8080/actuator/metrics
    * 2. Hit the http requests multiple times and refresh the above links
    * 3. You will find the controller methods in the above links
    * 4. Go to each method's url and click. eg: http://localhost:8080/actuator/metrics/saveEmployee
    * 5. You can find the metrics like count, total time, etc
    */
    @After("execution(* com.attraya.controller.*.*(..))")
    public void sendMetrics(JoinPoint joinPoint){
        log.info("ObservationRegistry.sendMetrics :: application collecting metrics");
        Observation.createNotStarted(joinPoint.getSignature().getName(), observationRegistry)
                .observe(()->joinPoint.getArgs());
        log.info("ObservationRegistry.sendMetrics :: application publishing metrics");
    }

}
