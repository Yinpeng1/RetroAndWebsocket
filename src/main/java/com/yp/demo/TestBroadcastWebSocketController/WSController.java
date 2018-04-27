package com.yp.demo.TestBroadcastWebSocketController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WSController {

    @RequestMapping("/")
    public String main(){
        return "main";
    }
}
