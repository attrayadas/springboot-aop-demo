package com.attraya.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

//    @Pointcut("execution(* com.attraya.*.*.*(..))")
//    @Pointcut("within(com.attraya..*)")
//    @Pointcut("target(com.attraya.service.EmployeeService)")
//    @Pointcut("execution(* com.attraya.service.EmployeeService.get*(int))")
    @Pointcut("execution(* com.attraya.controller.EmployeeController.*(..)) ||" +
            " execution(* com.attraya.service.EmployeeService.*(..))")
    private void logPointCut(){}

    @Before("logPointCut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("class name {}, method name {}, request body {}",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName(),
                new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

}
