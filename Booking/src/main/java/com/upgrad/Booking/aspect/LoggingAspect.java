package com.upgrad.Booking.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.upgrad.Booking..*(..))")
    public Object applyLogging(ProceedingJoinPoint joinPoint) throws Throwable{

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String className = signature.getDeclaringType().getSimpleName();
        String methodName = signature.getName();

        System.out.println("In "+ className+ ", inside method "+methodName);

        Object result = joinPoint.proceed();

        System.out.println("In "+ className+ ", existing method "+methodName);

        return result;

    }
}
