package com.xupu.appmanager_back.Controller;

import com.xupu.appmanager_back.Service.IAppService;
import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.Crash;
import com.xupu.appmanager_back.tools.FileTool;
import com.xupu.appmanager_back.tools.SpringBootTool;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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

    @PostMapping("/saveappversion")
    @ResponseBody
    public String saveAppversion(String po, MultipartFile file) {
        AppVersion appVersion = stringToObject(po, AppVersion.class);

        String fileName = file.getOriginalFilename().replace(".", "_" + System.currentTimeMillis() + ".");
        String savePath = SpringBootTool.getRootDir() + fileName;
        FileTool.savefile(file, savePath);

        appVersion.setFilelength(file.getSize());
        appVersion.setDownloadcount(0);
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

    /**
     * 多个照片上传
     *
     * @param uploadFiles
     * @param request
     * @return
     */

    public void upload(MultipartFile[] uploadFiles, HttpServletRequest request) {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        // 获取文件
        Map<String, MultipartFile> map = params.getFileMap();
    }

    @RequestMapping(value = "/downloadapp")
    public String downloadapp(HttpServletResponse res, Integer id) {
        AppVersion appVersion = appService.findById(id);
        appVersion.setDownloadcount(appVersion.getDownloadcount() + 1);
        if (appVersion == null) {
            respon(false, "文件不存在", null);
        } else {
            String filePath = SpringBootTool.getRootDir() + appVersion.getFilename();
            if (!new File(filePath).exists()) {
                respon(false, "文件不存在", null);
            } else {
                download(res, filePath);
                appService.save(appVersion);
            }
        }
        return respon(true, "", null);
    }


    public void download(HttpServletResponse res, String filePath) {
        String fileName = FileTool.getFileName(filePath);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
