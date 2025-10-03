package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class PreLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(PreLoggingAspect.class);

    @Before("@annotation(toLog)")
    public void logBeforeMethodExecution(JoinPoint joinPoint, ToLog toLog) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String customMessage = toLog.value().isEmpty() ? "" : " - " + toLog.value();

        switch (toLog.level()) {
            case INFO:
                logger.info("🟡 [PRE-ASPECT 1] Начало операции: {}.{}{}",
                        className, methodName, customMessage);
                break;
            case WARN:
                logger.warn("🟡 [PRE-ASPECT 1] Начало операции: {}.{}{}",
                        className, methodName, customMessage);
                break;
            case ERROR:
                logger.error("🟡 [PRE-ASPECT 1] Начало операции: {}.{}{}",
                        className, methodName, customMessage);
                break;
        }

        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            logger.debug("🟡 [PRE-ASPECT 1] Аргументы метода {}: {}", methodName, args);
        }
    }
}