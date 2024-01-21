package com.attraya.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAdvice {

//    @Pointcut("execution(* com.attraya.*.*.*(..))")
//    @Pointcut("within(com.attraya..*)")
//    @Pointcut("target(com.attraya.service.EmployeeService)")
//    @Pointcut("execution(* com.attraya.service.EmployeeService.get*(int))")
//    @Pointcut("execution(* com.attraya.controller.EmployeeController.*(..)) ||" +
//            " execution(* com.attraya.service.EmployeeService.*(..))")
    @Pointcut("execution(* com.attraya.*.*.*(..))")
    private void logPointCut(){}

    @Before("logPointCut()")
    public void logRequest(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice.logRequest :: class name {}, method name {}, request body {}",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName(),
                new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

    /* Uncomment for @AfterReturning demonstration*/
//    @AfterReturning(value = "execution(* com.attraya.controller.EmployeeController.*(..))", returning = "object")
//    public void logResponse(JoinPoint joinPoint, Object object) throws JsonProcessingException {
//        log.info("LoggingAdvice.logResponse :: class name {}, method name {}, request body {}",
//                joinPoint.getTarget().getClass(),
//                joinPoint.getSignature().getName(),
//                new ObjectMapper().writeValueAsString(object));
//    }

    @After(value = "execution(* com.attraya.controller.EmployeeController.*(..))")
    public void logResponse(JoinPoint joinPoint) throws JsonProcessingException {
        log.info("LoggingAdvice.logResponse :: class name {}, method name {}, request body {}",
                joinPoint.getTarget().getClass().getName(),
                joinPoint.getSignature().getName(),
                new ObjectMapper().writeValueAsString(joinPoint.getArgs()));
    }

}
