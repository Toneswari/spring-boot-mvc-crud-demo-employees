package com.luv2code.springboot.thymeleafdemo.aspect;




import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DemoLoggingAspect {
	
	private Logger logger=Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
	public void forControllerPackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
	public void forDAOPackage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
	public void forServicePackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forDAOPackage() || forServicePackage()")
	public void forAppFlow() {
		
	}
	
	@Before("forAppFlow()")
	public void before(JoinPoint thejoinpoint) {
		
		String method=thejoinpoint.getSignature().toShortString();
//		System.out.println("method signature "+ method);
		logger.info("In @before method called" + method);
		
		Object[] args= thejoinpoint.getArgs();
		
		for(Object ar: args) {
			
			logger.info("arguments "+ ar );
		}
		
	}
	
	@AfterReturning(pointcut = "forAppFlow()",returning = "result")
	public void Returnadvice(JoinPoint thjoinpoint,Object result) {
		
		String method=thjoinpoint.getSignature().toShortString();
		
		logger.info("In @After returning advice .."+method);
		
		logger.info("the result is "+ result);
		
	}
}
