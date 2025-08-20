package com.wipro.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SampleAspect {
	
	
	@Pointcut("execution( * com.wipro.service.userServiceJpa.findBy*(..))")
	public void afterService() {}
	
	@Before("serviceMethod()")
	public void beforeFindBy(JoinPoint joinPoint) {
		System.out.println("Inside @Before advice method..");
		System.out.println("JoinPoint method is"+ joinPoint.getSignature().getName());
		
		}
	@After("serviceMethod()")
	public void afterFindBy(JoinPoint joinPoint) {
		System.out.println("Inside  @After advice method.. ");
		System.out.println("JoinPoint method is: "+ joinPoint.getSignature().getName());


}
}