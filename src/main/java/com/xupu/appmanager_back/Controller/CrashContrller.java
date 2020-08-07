package com.xupu.appmanager_back.Controller;

import com.alibaba.fastjson.JSONObject;
import com.xupu.appmanager_back.Service.ICrashService;
import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.Crash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/crash")
public class CrashContrller extends BasicController {
    @Autowired
    private ICrashService crashService;
    @RequestMapping(value = "/findall")

    public String findAll() {
        List<Crash> crashes = crashService.findAll();
        return  respon(true,RESPON_MESSAGE_FIND_SUCCESS,crashes);
    }

    @PostMapping("/save")
    @ResponseBody
    public String save(String po) {
        Crash crash = crashService.save(stringToObject(po,Crash.class));
        return  respon(true,RESPON_MESSAGE_SAVE_SUCCESS,crash);
    }
    @PostMapping("/savelist")
    @ResponseBody
    public String saveList(String pos) {
        List<Crash> crash = crashService.saveList(stringToList(pos,Crash.class));
        return  respon(true,RESPON_MESSAGE_SAVE_SUCCESS,crash);
    }

    @PostMapping("/crashupdatebypoid")
    @ResponseBody
    public String crashUpdateByPOID(String po) {
        Crash crash =stringToObject(po,Crash.class);
        crash = crashService.flush(crash);
        if (crash == null) {
            return respon(false, RESPON_MESSAGE_UPDATE_ERROR, null);
        } else {
            return respon(true, RESPON_MESSAGE_UPDATE_SUCCESS, crash);
        }

    }

    private <T> List<T>  stringToList(String pos, Class<T> tClass) {
        List<T> ts = JSONObject.parseArray(pos, tClass);
        return ts;
    }
}
