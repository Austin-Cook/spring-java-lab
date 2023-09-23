package aspect;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerAspect {
    private Logger logger = Logger.getLogger("DAOLogger");

    public LoggerAspect() throws IOException {
        FileHandler fileHandler = new FileHandler("log.txt");
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.OFF);
        logger.addHandler(consoleHandler);
    }

    public void logReturn(Object returnValue) {
        if (returnValue == null) {
            logger.info("value(s) returned from DAO was: null");
        } else {
            logger.info("value(s) returned from DAO was: " + returnValue.toString());
        }
    }


    public Object logDAO(ProceedingJoinPoint joinPoint) throws Throwable {
        String method = joinPoint.toString();
        method = method.substring(method.lastIndexOf(".") + 1);
        logger.entering(joinPoint.getTarget().getClass().toString(), method);
        logger.info("The target class is " + joinPoint.getTarget().getClass().toString() +
                " and the method entered is " + method);
        Object returnObject = null;
        Instant start = Instant.now();
        try {
            returnObject = joinPoint.proceed();
            logger.info("value(s) returned was: " + returnObject.toString());
            logger.exiting(joinPoint.getTarget().getClass().toString(), method, returnObject);
        } catch (Exception ex) {
            logger.info("An exception was thrown during DataAccess: " + ex.toString());
            logger.throwing(joinPoint.getTarget().getClass().toString(), method, ex);
        } finally {
            Instant end = Instant.now();
            logger.info("DAO execution time: " + Duration.between(start, end).toMillis() + " milliseconds");
        }
        return returnObject;
    }

}
