package com.yp.demo.Controller;

import com.yp.demo.annotation.Logger;
import com.yp.demo.com.yp.test.okhttp.TestAllMethodGetlogisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestKuadiController {

    @Autowired
    private TestAllMethodGetlogisticsInfo test;

//    @Autowired
//    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/aiCha",method = RequestMethod.GET)
    public String getAicha(String waybill) {
        return "aicha==========="+ test.getlogisticsInfoFromAiCha(waybill);
    }

    @RequestMapping(value = "/kuaidDi",method = RequestMethod.GET)
    public String getKuaidi(String waybill) {
        return "kuaidi==========="+ test.getLogisticsInfoFromKuaiDi(waybill);
    }

    @RequestMapping(value = "/otherKuaiDi100",method = RequestMethod.GET)
    public String getKuaidi100(String waybill) {
        return "kuaidi100==========="+ test.getLogisticsInfoFromOtherKuaidiInterface(waybill);
    }

//    @RequestMapping(value = "/send", method = RequestMethod.GET)
//    public String sendKafka(HttpServletRequest request, HttpServletResponse response) {
//        try {
//            String message = request.getParameter("message");
////            logger.info("kafka的消息={}"， message);
//            System.out.println("kafka的消息="+ message);
//            kafkaTemplate.send("test", "key", message);
//            System.out.println("发送kafka成功.");
////            return new Response(ResultCode.SUCCESS, "发送kafka成功", null);
//            return "发送成功";
//        } catch (Exception e) {
////            logger.error("发送kafka失败", e);
//            System.out.println("发送失败");
////            return new Response(ResultCode.EXCEPTION, "发送kafka失败", null);
//            return "发送失败";
//        }
//    }

//    @Scheduled(cron = "0/15 * * * * *")
//    public void dingShiqi(){
//        System.out.println("aicha==========="+ test.getlogisticsInfoFromAiCha("3839998850701"));
//        System.out.println("kuaidi==========="+ test.getLogisticsInfoFromKuaiDi("3839998850701"));
//        System.out.println("kuaidi100==========="+ test.getLogisticsInfoFromOtherKuaidiInterface("3839998850701"));
//    }
}
