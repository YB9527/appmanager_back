package com.xupu.appmanager_back.po;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class DownUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer downcount;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    private AppVersion appversion;

    @OneToOne
    @JoinColumn(name="userid",referencedColumnName="id")
    private User user;

    public DownUser(){

    }

    public DownUser(AppVersion appversion, User user) {
        this.appversion = appversion;
        this.user = user;
        this.downcount = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDowncount() {
        return downcount==null?0:downcount;
    }

    public void setDowncount(Integer downcount) {
        this.downcount = downcount;
    }

    public AppVersion getAppversion() {
        return appversion;
    }

    public void setAppversion(AppVersion appversion) {
        this.appversion = appversion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DownUser downUser = (DownUser) o;
        return appversion.equals(downUser.appversion) &&
                user.equals(downUser.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appversion, user);
    }
}
