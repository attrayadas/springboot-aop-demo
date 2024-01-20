package com.attraya.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {


    @Before("execution(* com.attraya.*.*.*(..))") // point cut expression
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {}, method name {}, request body {}",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName(),
                new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

}
