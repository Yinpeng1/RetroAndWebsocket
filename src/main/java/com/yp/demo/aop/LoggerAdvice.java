package com.yp.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Component
@Aspect
public class LoggerAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Before("within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("执行--" + loggerManage.description() + "--开始");
        startTime.set(System.currentTimeMillis());
        logger.info(joinPoint.getSignature().toString());
//        logger.info(parseParames(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)", returning = "result")
    public void addAfterReturningLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage, Object result) {
        logger.info("执行--" + loggerManage.description() + "--结束");
        logger.info("执行时间--" + (System.currentTimeMillis() - startTime.get()));
        logger.info("返回结果是:===="+result);
    }

    @AfterThrowing(pointcut = "within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage, Exception ex) {
        logger.error("执行--" + loggerManage.description() + "--异常", ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer();
        for (Object obj : parames) {
//            String va = ToStringBuilder.reflectionToString(obj);
            param.append("参数--").append(obj.toString());
        }
        return param.toString();
    }

}
