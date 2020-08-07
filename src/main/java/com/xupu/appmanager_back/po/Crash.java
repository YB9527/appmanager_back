package com.xupu.appmanager_back.po;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Crash   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  userid;//用户id
    private String username;//用户名称
    private Long datetime;//crash日期时间
    @Lob
    private String info;//详细信息
    private String phonename;//手机品牌
    private String phonetype;//手机型号
    private Boolean ishandle;//是否已处理
    private String handlemessage;//处理信息
    private String version;//软件的版本号
    private Integer versioncode;//软件的版本的数字
    @Transient
    private AppVersion appVersion;

    public Crash(){

    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getDatetime() {
        return datetime;
    }

    public void setDatetime(Long datetime) {
        this.datetime = datetime;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    public Boolean getIshandle() {
        return ishandle;
    }

    public void setIshandle(Boolean ishandle) {
        this.ishandle = ishandle;
    }

    public String getHandlemessage() {
        return handlemessage;
    }

    public void setHandlemessage(String handlemessage) {
        this.handlemessage = handlemessage;
    }

    public String getPhonename() {
        return phonename;
    }

    public void setPhonename(String phonename) {
        this.phonename = phonename;
    }

    public Integer getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(Integer versioncode) {
        this.versioncode = versioncode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crash that = (Crash) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
