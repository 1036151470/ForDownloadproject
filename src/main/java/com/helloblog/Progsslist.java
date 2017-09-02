package com.helloblog;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class Progsslist {

    public static Map<String, Float> map = new ConcurrentHashMap<>();


    @PostMapping("/getprogress")
    @ResponseBody
    public String getprogress(String forid) {
        System.out.println(map.get(forid));
        return String.valueOf(map.get(forid));
    }
}
