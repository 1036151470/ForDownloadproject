package com.helloblog;

import com.alibaba.fastjson.JSON;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Controller
public class Messagescontroller {

    @GetMapping("/getsystemmessage")
    @ResponseBody
    public Object getsystemmessage() throws Exception {
        Properties props = System.getProperties();
        Map<String,String> monitorMap = new HashMap<>();
        monitorMap.put("osName", props.getProperty("os.name"));// 操作系统的名称
        monitorMap.put("arch", props.getProperty("os.arch"));// 操作系统的构架
        monitorMap.put("osVersion", props.getProperty("os.version"));// 操作系统的版本
        monitorMap.put("javaVersion", props.getProperty("java.version"));// Java的运行环境版本
        monitorMap.put("vendor", props.getProperty("java.vendor"));// Java的运行环境供应商
        monitorMap.put("javaUrl", props.getProperty("java.vendor.url"));// Java供应商的URL
        monitorMap.put("javaHome", props.getProperty("java.home"));// Java的安装路径
        monitorMap.put("tmpdir", props.getProperty("java.io.tmpdir"));// 默认的临时文件路径
        return JSON.toJSON(monitorMap);
    }


    @GetMapping("/getcpu")
    @ResponseBody
    public String getcpu() throws Exception {
        Sigar sigar = new Sigar();
        CpuPerc cpuList = sigar.getCpuPerc();
        int cpuper = (int) (cpuList.getCombined() * 100);
        return String.valueOf(cpuper);
    }

    @GetMapping("/getmemory")
    @ResponseBody
    public Object getmemory() throws Exception {
        Map<String,String> memorymap = new HashMap<>();
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        int getFreePercent = (int) mem.getFreePercent();
        double getTotal =  mem.getTotal()/(1024*1024*1024);

        memorymap.put("getFreePercent",String.valueOf(getFreePercent));
        memorymap.put("getTotal",String.valueOf(getTotal));
        return JSON.toJSON(memorymap);
    }

}
