package com.yp.demo.Controller;


import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.InetAddress;

public class ElkLog4jTest {
    private static final Logger logger = Logger.getLogger(ElkLog4jTest.class);
    public static void main(String[] args) throws Exception {
        InetAddress addr = InetAddress.getLocalHost();
        String ip=addr.getHostAddress().toString(); //获取本机
        System.out.println(ip);
        String hostName=addr.getHostName().toString(); //获取本机计算机名称
        System.out.println(hostName);
//        logger.debug("This is a debug message!");
//        logger.info("This is info message!");
//        logger.warn("This is a warn message!");
//        logger.error("This is error message!");
//
        try{
            System.out.println(5/0);
        }catch(Exception e){
            throw new Exception("cuowu");
//            logger.error(e);
        }
    }

}
