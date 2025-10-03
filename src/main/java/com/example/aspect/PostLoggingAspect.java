package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class PostLoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(PostLoggingAspect.class);

    @AfterReturning(pointcut = "@annotation(toLog)", returning = "result")
    public void logAfterSuccessfulExecution(JoinPoint joinPoint, ToLog toLog, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String customMessage = toLog.value().isEmpty() ? "" : " - " + toLog.value();

        switch (toLog.level()) {
            case INFO:
                logger.info("🟢 [POST-ASPECT 2] Операция успешно завершена: {}.{}{}",
                        className, methodName, customMessage);
                break;
            case WARN:
                logger.warn("🟢 [POST-ASPECT 2] Операция успешно завершена: {}.{}{}",
                        className, methodName, customMessage);
                break;
            case ERROR:
                logger.error("🟢 [POST-ASPECT 2] Операция успешно завершена: {}.{}{}",
                        className, methodName, customMessage);
                break;
        }

        if (result != null) {
            logger.debug("🟢 [POST-ASPECT 2] Результат метода {}: {}", methodName, result);
        }
    }

    @After("@annotation(toLog)")
    public void logAfterMethodExecution(JoinPoint joinPoint, ToLog toLog) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();

        logger.info("🔵 [POST-ASPECT 2] Операция завершена: {}.{}", className, methodName);
    }

    @AfterThrowing(pointcut = "@annotation(toLog)", throwing = "exception")
    public void logAfterException(JoinPoint joinPoint, ToLog toLog, Exception exception) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String customMessage = toLog.value().isEmpty() ? "" : " - " + toLog.value();

        logger.error("🔴 [POST-ASPECT 2] Ошибка при выполнении операции: {}.{}{} - {}",
                className, methodName, customMessage, exception.getMessage());
    }
}