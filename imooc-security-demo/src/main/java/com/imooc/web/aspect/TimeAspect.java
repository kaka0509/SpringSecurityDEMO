package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;
// 切面拿不到原始Http请求的信息
@Aspect
@Component
public class TimeAspect {
    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is : " + arg);
        }
        Long start = new Date().getTime();
        Object proceed = pjp.proceed();

        System.out.println("time aspect end ");
        return proceed;
    }
}
