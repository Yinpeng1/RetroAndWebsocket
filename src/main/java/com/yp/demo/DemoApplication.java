package com.yp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableWebSocket
public class DemoApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
//    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("qwe");
        list.add("qwe");
        list.add("123");
        list.add("1234");
        list.add("1234");
        //一定要同步，不然多线程读取list会出现问题
        final List<String> list2= Collections.synchronizedList(new ArrayList<>());
        list.parallelStream().distinct().forEach(e -> {
            System.out.println(e);
            list2.add(e);
        });
        System.out.println(list2.toString());
    }
}
