package com.xupu.appmanager_back.Service;

import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.DownUser;
import com.xupu.appmanager_back.po.User;

import java.util.List;

public interface IAppService {
    AppVersion save(AppVersion appVersion);

    List<AppVersion> findAll();

    void deleteById(Integer id);

    AppVersion findById(Integer id);

    /**
     * 查找最新的app
     * @return
     */
    AppVersion findNewsApp();

    AppVersion flush(AppVersion appVersion);

    DownUser findByAppVersionAndUser(AppVersion appVersion, User user);
}
