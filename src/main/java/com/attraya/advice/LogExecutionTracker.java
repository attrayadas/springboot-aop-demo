package com.attraya.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogExecutionTracker {

    @Around("@annotation(com.attraya.annotation.TrackExecutionTime)")
    public Object logExecutionDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        // before
        long startTime = System.currentTimeMillis();
        Object object = proceedingJoinPoint.proceed(); // it will call the actual method (joinpoint)
        long endTime = System.currentTimeMillis();
        log.info("LogExecutionTracker.logExecutionDuration :: method {} execution takes {} ms times to complete...",
                proceedingJoinPoint.getSignature(), (endTime-startTime));
        return object;
    }
}
