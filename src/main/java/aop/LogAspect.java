package aop;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* service.serviceImpl.UserServiceImpl.*(..))")
    public void logAop() {
        logger.info("logAOP");
    }

    @Before("logAop()")
    public void logBefore() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        ServletRequest request = attributes.getRequest();

        System.out.println("log Before");
        logger.info("url = {}", ((HttpServletRequest) request).getRequestURL());
        logger.info("method = {}", ((HttpServletRequest) request).getMethod());
        logger.info("ip = {}", request.getRemoteAddr());
    }

    @AfterReturning(returning = "o", pointcut = "logAop()")
    public void logAfterReturning(Object o) {
        System.out.println("返回通知AfterReturning" + o);
        logger.info("返回通知AfterReturning");
    }

    @After("logAop() && args(name)")
    public void logAfter(String name) {
        logger.info(name + "后置通知After");
    }

    @AfterThrowing("logAop()")
    public void logAfterThrow() {
        logger.info("异常通知AfterThrowing");
    }
}