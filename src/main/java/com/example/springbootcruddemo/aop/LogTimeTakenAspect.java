package com.example.springbootcruddemo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
@Slf4j
public class LogTimeTakenAspect {

    @Around("@annotation(logTimeTaken)")
    public Object logTimeForAnnotatedMethods(ProceedingJoinPoint proceedingJoinPoint, LogTimeTaken logTimeTaken) throws Throwable {
        return logExecutionTime(proceedingJoinPoint, logTimeTaken.warningMillisecondsThreshold());
    }

    @Around("within(com.example.springbootcruddemo.repository..*) || within(@org.springframework.stereotype.Repository *)")
    public Object logTimeForRepositoryMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        final long DEFAULT_THRESHOLD = 1000;
        return logExecutionTime(proceedingJoinPoint, DEFAULT_THRESHOLD);
    }

    private Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint, long threshold) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long elapsedMilliseconds = System.currentTimeMillis() - start;

        if (elapsedMilliseconds > threshold) {
            log.warn("Processing method [{}] took {} ms. Args: {}",
                    proceedingJoinPoint.getSignature().getName(),
                    elapsedMilliseconds,
                    Arrays.toString(proceedingJoinPoint.getArgs()));
        }

        return result;
    }
}

