package com.example.springbootcruddemo.aop;

@interface LogTimeTaken {
    long warningMillisecondsThreshold() default Long.MAX_VALUE;
}
