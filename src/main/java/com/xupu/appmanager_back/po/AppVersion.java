package com.xupu.appmanager_back.po;



import com.xupu.appmanager_back.tools.SpringBootTool;

import javax.persistence.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Entity
public class AppVersion  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String levelcontent;
    private Boolean isforce;
    private String version;
    private String filename;
    private Long filelength;
    private Long uploaddate;//上传日期
    private Integer downloadcount;//下载次数
    @Transient
    private List<Crash> crashs;

    public AppVersion(){

    }

    public AppVersion(String appversion, String title, String levelcontent, long filelength, Boolean force) {
        this.title = title;
        this.levelcontent = levelcontent;
        this.isforce = force;
        this.version =appversion;
        this.filelength = filelength;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevelcontent() {
        return levelcontent;
    }

    public void setLevelcontent(String levelcontent) {
        this.levelcontent = levelcontent;
    }

    public Boolean getIsforce() {
        return isforce;
    }

    public void setIsforce(Boolean isforce) {
        this.isforce = isforce;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Long getFilelength() {
        return filelength;
    }

    public void setFilelength(Long filelength) {
        this.filelength = filelength;
    }

    public Long getUploaddate() {
        return uploaddate;
    }

    public void setUploaddate(Long uploaddate) {
        this.uploaddate = uploaddate;
    }

    public Integer getDownloadcount() {
        return downloadcount;
    }

    public void setDownloadcount(Integer downloadcount) {
        this.downloadcount = downloadcount;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<Crash> getCrashs() {
        return crashs;
    }

    public void setCrashs(List<Crash> crashs) {
        this.crashs = crashs;
    }

    public String getDownloadUrl() {
        try {
            return InetAddress.getLocalHost()+""+ SpringBootTool.getRootDir()+getFilename();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
