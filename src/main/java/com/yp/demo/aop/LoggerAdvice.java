package com.yp.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    ThreadLocal<Long> startTime = new ThreadLocal<>();


    @Before("within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)")
    public void addBeforeLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage) {
        logger.info("执行--" + loggerManage.description() + "--开始");
        startTime.set(System.currentTimeMillis());
        logger.info(joinPoint.getSignature().toString());
        logger.info(parseParames(joinPoint.getArgs()));
    }

    @AfterReturning("within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)")
    public void addAfterReturningLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage) {
        logger.info("执行--" + loggerManage.description() + "--结束");
        logger.info("执行时间--" + (System.currentTimeMillis() - startTime.get()));
    }

    @AfterThrowing(pointcut = "within(com.yp.demo.Controller.TestController) && @annotation(loggerManage)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, com.yp.demo.annotation.Logger loggerManage, Exception ex) {
        logger.error("执行--" + loggerManage.description() + "--异常", ex);
    }

    private String parseParames(Object[] parames) {
        if (null == parames || parames.length <= 0) {
            return "";
        }
        StringBuffer param = new StringBuffer("参数--");
        for (Object obj : parames) {
//            String va = ToStringBuilder.reflectionToString(obj);
            param.append(obj.toString()).append("  ");
        }
        return param.toString();
    }

}
