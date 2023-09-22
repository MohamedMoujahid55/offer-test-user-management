package fr.air.france.technicaltestairfrance.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * This is an Aspect class dedicated to log inputs and outputs as well as the processing time for all method in the classes under web/controller package
 *
 * @author MOUJAHID Mohamed
 * @version 1.0
 */
@Aspect
@Component
public class LoggingAspect {

    Logger log = LoggerFactory.getLogger(LoggingAspect.class);
    ObjectMapper objectMapper = new ObjectMapper();

    /**
     * This method executed around the execution of each method in the classes under web/controller package.
     * It contains logging of the method signature and its execution time.
     * @param joinPoint - {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable
     */
    @Around("execution(* fr.air.france.technicaltestairfrance.controllers.*.*(..))")
    public Object beforeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("==============Start Logging==============");
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        log.info("Method: {} executed in {} ms", joinPoint.getSignature(), executionTime);

        return proceed;
    }

    /**
     * This method executed after a successful execution of each method in the classes under web/controller package.
     * It contains logging of the input and output of the method.
     *
     * @param joinPoint   {@link JoinPoint}
     * @param returnValue {@link Object}
     * @throws JsonProcessingException {@link JsonProcessingException}
     */
    @AfterReturning(value = "execution(* fr.air.france.technicaltestairfrance.controllers.*.*(..))", returning = "returnValue")
    public void afterReturning(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {

        log.info("Inputs: ");
        String input = "";
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            input = objectMapper.writeValueAsString(joinPoint.getArgs()[0]);

        }
        log.info(input);
        log.info("Outputs: ");
        String output = "";
        if (!ObjectUtils.isEmpty(returnValue)) {
            output = objectMapper.writeValueAsString(((ResponseEntity<?>) returnValue).getBody());
        }
        log.info(output);
    }

    /**
     * This method executed after a failed execution of each method in the classes under web/controller package.
     * It contains logging of the input and the error raised by the method.
     *
     * @param joinPoint {@link JoinPoint}
     * @param e         {@link Exception}
     * @throws JsonProcessingException {@link JsonProcessingException}
     */
    @AfterThrowing(value = "execution(* fr.air.france.technicaltestairfrance.controllers.*.*(..))", throwing = "e")
    public void afterThrowingAccessDeniedException(JoinPoint joinPoint, Exception e) throws JsonProcessingException {
        log.info("Inputs: ");
        String input = "";
        if (!ObjectUtils.isEmpty(joinPoint.getArgs())) {
            input = objectMapper.writeValueAsString(joinPoint.getArgs()[0]);
        }
        log.info(input);

        log.info("Outputs: ");
        log.info(e.getMessage());
    }

    /**
     * This method executed after a successful/failed execution of each method in the classes under web/controller package.
     * It indicates the end of logging.
     */
    @After("execution(* fr.air.france.technicaltestairfrance.controllers.*.*(..))")
    public void afterCompletion() {

        log.info("==============End Logging==============");
    }
}
