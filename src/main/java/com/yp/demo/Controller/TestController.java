package com.yp.demo.Controller;

import com.yp.demo.annotation.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class TestController {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    @Logger(description = "hello")
    public String getUserName(String userID, Integer age) {
        return "ArcherLj";
    }

    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("coinType","BTC");
        map.put("userCode","4124145412");
        map.put("scdef","4124145412");
        map.put("cfrds","4124145412");
        map.put("vfrt","4124145412");
        map.entrySet().stream().forEach(entry ->
            System.out.println("entry.getKey()+"+entry.getKey()+"================="+"entry.getValue()+"+entry.getValue())
        );
    }
}
