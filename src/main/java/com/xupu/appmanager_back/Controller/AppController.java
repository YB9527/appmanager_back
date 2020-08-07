package com.xupu.appmanager_back.Controller;

import com.xupu.appmanager_back.Service.IAppService;
import com.xupu.appmanager_back.Service.UserService;
import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.Crash;
import com.xupu.appmanager_back.po.DownUser;
import com.xupu.appmanager_back.po.User;
import com.xupu.appmanager_back.tools.FileTool;
import com.xupu.appmanager_back.tools.SpringBootTool;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.java2d.pipe.AlphaPaintPipe;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/app")
public class AppController extends BasicController {

    @Autowired
    private IAppService appService;
    @Autowired
    private UserService userService;

    @PostMapping("/saveappversion")
    @ResponseBody
    public String saveAppversion(String po, MultipartFile file) {
        AppVersion appVersion = stringToObject(po, AppVersion.class);

        String fileName = file.getOriginalFilename().replace(".", "_" + System.currentTimeMillis() + ".");
        String savePath = SpringBootTool.getRootDir() + fileName;
        FileTool.savefile(file, savePath);
        appVersion.setFilelength(file.getSize());
        appVersion.setFilename(fileName);
        appVersion.setUploaddate(System.currentTimeMillis());
        AppVersion newAppVersion = appService.save(appVersion);
        return respon(true, RESPON_MESSAGE_SAVE_SUCCESS, newAppVersion);
    }

    @PostMapping("/appupdatebypoid")
    @ResponseBody
    public String appUpdateByPOID(String po) {
        AppVersion appVersion = stringToObject(po, AppVersion.class);
        appVersion = appService.flush(appVersion);
        if (appVersion == null) {
            return respon(false, RESPON_MESSAGE_UPDATE_ERROR, null);
        } else {
            return respon(true, RESPON_MESSAGE_UPDATE_SUCCESS, appVersion);
        }
    }

    @RequestMapping(value = "/findall")
    public String findAll() {
        List<AppVersion> appVersions = appService.findAll();
        return respon(true, RESPON_MESSAGE_FIND_SUCCESS, appVersions);
    }

    @RequestMapping(value = "/deletebyid")
    public String deleteById(Integer id) {
        if (id == null) {
            return respon(false, RESPON_MESSAGE_DELETE_ERROR, null);
        } else {
            appService.deleteById(id);
            return respon(true, RESPON_MESSAGE_DELETE_SUCCESS, null);
        }
    }


    @RequestMapping(value = "/downloadapp")
    public String downloadapp(HttpServletResponse res, Integer id, String user) {
        User userPo = stringToObject(user, User.class);
        AppVersion appVersion = appService.findById(id);
        if (userPo == null) {
            //老版本没有传user
            userPo = userService.findWebUser();
        }
        return downloadapp(res, appVersion, userPo);
    }

    /**
     * 网页下载 app
     *
     * @param res
     * @return
     */
    @RequestMapping(value = "/downloadnewsapp")
    public String downloadnewsapp(HttpServletResponse res) {
        AppVersion appVersion = appService.findNewsApp();
        return downloadapp(res, appVersion, userService.findWebUser());
    }

    /**
     * 下载app
     *
     * @param res
     * @param appVersion app
     * @param user   下载用户
     * @return
     */
    public String downloadapp(HttpServletResponse res, AppVersion appVersion, User user) {

        if (appVersion != null && user != null) {
            String filePath = SpringBootTool.getRootDir() + appVersion.getFilename();
            if (!new File(filePath).exists()) {
                return respon(false, "文件不存在", null);
            } else {
                FileTool.DownLoadFile(res, filePath);
                DownUser downUser = appService.findByAppVersionAndUser(appVersion, user);
                if (downUser == null) {
                    downUser = new DownUser(appVersion, user);
                }

                int index = appVersion.getDownusers().indexOf(downUser);
                if (index == -1) {
                    appVersion.getDownusers().add(downUser);
                } else {
                    downUser = appVersion.getDownusers().get(index);
                }
                downUser.setDowncount(downUser.getDowncount() + 1);
                downUser = userService.saveDownUser(downUser);
                return respon(true, "下载成功", downUser);
            }
        }
        return respon(false, "文件不存在", null);
    }

    /**
     * 得到app信息
     *
     * @return
     */
    @ResponseBody
    @GetMapping("/getnewestappinfo")
    public String getNewestAppInfo() {
        AppVersion appVersion = appService.findNewsApp();
        if (appVersion != null) {
            return respon(true, "", appVersion);
        }
        return responBasicError();
    }


}
