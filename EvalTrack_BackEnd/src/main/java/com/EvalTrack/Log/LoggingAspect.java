package com.EvalTrack.Log;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component

public class LoggingAspect {
	 private static final Logger LG = Logger.getLogger(LoggingAspect.class);
	 @Before("execution(* com.EvalTrack..*.*(..))")
	public void logMethodEntry(JoinPoint joinPoint) {
	    String name = joinPoint.getSignature().getName();
	    LG.info("Appel de la méthode " + name + " : ");
	}


}
