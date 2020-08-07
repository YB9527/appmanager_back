package com.xupu.appmanager_back;

import com.alibaba.fastjson.JSONObject;
import com.xupu.appmanager_back.po.Respon;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AppmanagerBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppmanagerBackApplication.class, args);
    }
    @RestController
    public class HelloController {
        @RequestMapping("/hello")
        public String hello() {
            return "Hello Spring Boot!";
        }
    }
    @Test
    public void fun(){
        String data= "{\"code\":0,\"data\":\"{\\\"downloadUrl\\\":\\\"YB/192.168.10.34C:/AppManager/调绘专家1_1596701020260.1_1596701020260.2_1596701020260.200804_beta_1596701020260.apk\\\",\\\"downloadcount\\\":0,\\\"filelength\\\":58075589,\\\"filename\\\":\\\"调绘专家1_1596701020260.1_1596701020260.2_1596701020260.200804_beta_1596701020260.apk\\\",\\\"id\\\":3,\\\"isforce\\\":false,\\\"levelcontent\\\":\\\"1、每个图层颜色不一样\\\\n2、修复 修改数据的bug\\\\n3、添加地图选中shape颜色\\\",\\\"title\\\":\\\"App更新\\\",\\\"uploaddate\\\":1596701020538,\\\"version\\\":\\\"1.1.2.200804_beta\\\"}\",\"msg\":\"YB/192.168.10.34C:/AppManager/调绘专家1_1596701020260.1_1596701020260.2_1596701020260.200804_beta_1596701020260.apk\",\"ok\":true}";
        Respon respon = JSONObject.parseObject(data,Respon.class);
        String aa = "123";
    }
}
