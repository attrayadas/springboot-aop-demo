package com.attraya.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomLoggingFramework {

    // Use debugging to get the complete flow of code
    // before & after returning
    @Around("execution(* com.attraya.controller.EmployeeController.*(..))")
    public Object captureRequestAndResponse(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        // execute your before logic
        System.out.println("-----------------BEFORE STARTS-----------------");
        log.info("CustomLoggingFramework.captureRequestAndResponse :: execution started {} , Request body {}",
                proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
        System.out.println("-----------------BEFORE ENDS-------------------");

        Object object = proceedingJoinPoint.proceed();

        // after logic
        System.out.println("-----------------AFTER STARTS------------------");
        log.info("CustomLoggingFramework.captureRequestAndResponse :: execution started {} , Request body {}",
                proceedingJoinPoint.getSignature(), proceedingJoinPoint.getArgs());
        System.out.println("-----------------AFTER ENDS--------------------");

        return object;
    }
}
