package com.yp.demo.Controller;

import com.yp.demo.annotation.Logger;
import jnr.ffi.annotations.In;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @Logger(description = "hello")
    public String getUserName(String userID, Integer age) {
        return "ArcherLj";
    }

    public static void main(String[] args) {
//        Map<String, String> map = new LinkedHashMap<String, String>();
//        map.put("coinType","BTC");
//        map.put("userCode","4124145412");
//        map.put("scdef","4124145412");
//        map.put("cfrds","4124145412");
//        map.put("vfrt","4124145412");
//        map.entrySet().stream().forEach(entry ->
//            System.out.println("entry.getKey()+"+entry.getKey()+"================="+"entry.getValue()+"+entry.getValue())
//        );
        List<String> list = new ArrayList();
//        .stream().filter(t -> !t.equals(1)).collect(Collectors.toList())
        list.add("11");
        list.add("11");
        list.add("22");
        List<String> list1 = new ArrayList<>();
//        List s = list.stream().map(p -> ).collect(Collectors.toList());
//        s.forEach(System.out::println);
//        String s = "";
//        long l = list.stream().filter(p -> p).count();
//        System.out.println(l);

        Map<String, Long> map = list.stream().collect(Collectors.groupingBy(p -> p,Collectors.counting()));
        map.forEach((k, v) -> System.out.println(k + ":" + v));



//        System.out.println(System.currentTimeMillis());
//        System.out.println(stampToDate("1532403383823"));
//        System.out.println(timeStamp2Date("1528099898", null));
//        System.out.println(timeStamp2Date("1528100721", null));
//        System.out.println(timeStamp2Date("1528100936", null));
//
//        System.out.println(timeStamp2Date("1528094424", null));
//        System.out.println(timeStamp2Date("1528094442", null));
//        System.out.println(timeStamp2Date("1528094480", null));
//        System.out.println(timeStamp2Date("1528099898", null));
    }


    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
}
