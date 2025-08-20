

package com.wipro.aspect;
import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
/*
* Separation of concerns:
* Objective: The log statements in Controller class to be removed and managed by Aspect class.
*/
@Aspect
@Component
@Slf4j
public class LogAspect {
	
	/**
	 * Pointcut that matches all Spring beans in the application's service package.
	 */
	@Pointcut("within(com.wipro.service.*)")
	public void applicationPackagePointcut() {
		// Method is empty as this is just a Pointcut, the implementations are in the advices.
	}
	/**
	 * Advice that logs methods throwing exceptions.
	 *
	 * @param joinPoint join point for advice
	 * @param e exception
	 */
	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(), e.getCause() != null? e.getCause() : "NULL");
	}
	/**
	 * Advice that logs when a method is entered and exited.
	 *
	 * @param joinPoint join point for advice
	 * @return result
	 * @throws Throwable throws IllegalArgumentException
	 */
	@Around("applicationPackagePointcut()")
	public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			if (log.isInfoEnabled()) {
				log.info("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
						joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
			}
			try {
				Object result = joinPoint.proceed(); // Explicitly invoking the joinPoint method
				if (log.isInfoEnabled()) {
					log.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
							joinPoint.getSignature().getName(), result);
				}
				return result;
			} catch (Exception e) {
				log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
						joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
				throw e;
			}
		}catch(Exception e) {
				throw e;
		}
	}
}
