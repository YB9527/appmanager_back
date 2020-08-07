package com.xupu.appmanager_back.Controller;

import com.alibaba.fastjson.JSONObject;
import com.xupu.appmanager_back.po.Respon;

public class BasicController {
    protected final String RESPON_MESSAGE_SAVE_SUCCESS = "保存成功";
    protected final String RESPON_MESSAGE_SAVE_ERROR = "保存失败";
    protected final String RESPON_MESSAGE_FIND_SUCCESS = "查找成功";
    protected final String RESPON_MESSAGE_DELETE_SUCCESS = "删除成功";
    protected final String RESPON_MESSAGE_DELETE_ERROR = "删除失败";

    protected final String RESPON_MESSAGE_UPDATE_SUCCESS = "修改成功";
    protected final String RESPON_MESSAGE_UPDATE_ERROR = "修改失败";


    protected String responBasicError() {
        Respon respon = new Respon(false, 1, "失败", "{}");
        String result = JSONObject.toJSONString(respon);
        return result;
    }

    public String respon(Boolean isOk, String message, Object object) {
        Respon respon = getRespon(isOk, 0, message, object);
        String result = JSONObject.toJSONString(respon);
        return result;
    }

    public Respon getRespon(Boolean isOk, Integer code, String message, Object object) {
        String json;
        if (object == null) {
            json = "{}";
        } else {
            json = JSONObject.toJSONString(object);
        }
        Respon respon = new Respon(isOk, code, message, json);
        return respon;
    }

    public <T> T stringToObject(String json, Class<T> tClass) {
        T t = JSONObject.parseObject(json, tClass);
        return t;
    }
}
