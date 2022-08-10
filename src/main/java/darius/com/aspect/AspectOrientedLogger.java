package darius.com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectOrientedLogger {
    private final FileLogger fileLogger;

    public AspectOrientedLogger() {
        fileLogger = new FileLogger(Constants.FULL_FILE_PATH);
    }

    @Before("execution(* darius.com.dao.TicketDAO.login(..))")
    public void logBeforeLogin(JoinPoint joinPoint) {
        fileLogger.writeToFile("Before login method");
        String username = (String) joinPoint.getArgs()[0];
        String password = (String) joinPoint.getArgs()[1];
        fileLogger.writeToFile("Arguments for log in - Username:" + username + " ,password:" + password);
    }

    @After("execution(* darius.com.dao.TicketDAO.showTrains(..))")
    public void logAfterShowTrains(JoinPoint joinPoint) {
        fileLogger.writeToFile("After show trains method");
    }

    @After("execution(* darius.com.dao.TicketDAO.bookTicket(..))")
    public void logAfterBookTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After book ticket method");
    }

    @AfterThrowing("execution(* darius.com.dao.TicketDAO.login(..))")
    public void logAfterThrowingLogin(JoinPoint joinPoint) {
        fileLogger.writeToFile("After throwing login method");
    }

    @AfterThrowing(pointcut = "execution(* darius.com.dao.TicketDAO.bookTicket(..))")
    public void logAfterThrowingBookTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After throwing book ticket method");
    }

    @AfterThrowing(pointcut = "execution(* darius.com.dao.TicketDAO.cancelTicket(..))")
    public void logAfterThrowingCancelTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After throwing cancel ticket method");
    }

    @AfterReturning(pointcut = "execution(* darius.com.dao.TicketDAO.bookTicket(..))")
    public void logAfterReturningBookTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After returning book ticket method");
    }

    @After("execution(* darius.com.dao.TicketDAO.cancelTicket(..))")
    public void logAfterCancelTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After cancel ticket method");
    }

    @AfterReturning(pointcut = "execution(* darius.com.dao.TicketDAO.cancelTicket(..))")
    public void logAfterReturningCancelTicket(JoinPoint joinPoint) {
        fileLogger.writeToFile("After returning cancel ticket method");
    }

}
