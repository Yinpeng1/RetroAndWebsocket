package com.yp.demo.Controller;

import com.yp.demo.annotation.Logger;
import com.yp.demo.com.yp.test.okhttp.TestAllMethodGetlogisticsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestKuadiController {

    @Autowired
    private TestAllMethodGetlogisticsInfo test;

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

    @Scheduled(cron = "0/15 * * * * *")
    public void dingShiqi(){
        System.out.println("aicha==========="+ test.getlogisticsInfoFromAiCha("3839998850701"));
        System.out.println("kuaidi==========="+ test.getLogisticsInfoFromKuaiDi("3839998850701"));
        System.out.println("kuaidi100==========="+ test.getLogisticsInfoFromOtherKuaidiInterface("3839998850701"));
    }
}
