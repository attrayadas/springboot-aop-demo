package com.attraya.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionAlertAdvice {

    @Pointcut("execution(* com.attraya.*.*.*(..))")
    public void alertFor(){

    }

    @AfterThrowing(value = "alertFor()", throwing = "exception")
    public void captureErrorAndSetAlerts(JoinPoint joinPoint, Exception exception){
        log.error("ExceptionAlertAdvice.captureErrorAndSetAlerts :: Exception occurred in {}, Exception message : {}",joinPoint.getSignature(), exception.getMessage());

        if (exception instanceof IllegalArgumentException){
            // set the alert
            // trigger an email to DEV team
            log.error(">>WRONG INPUT ILLEGAL ARGUMENT<<");
        } if (exception instanceof RuntimeException){
            log.error(">>WRONG INPUT RUNTIME EXCEPTION<<");
        }
    }
}
