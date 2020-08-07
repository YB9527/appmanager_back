package com.xupu.appmanager_back.Service;

import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.DownUser;
import com.xupu.appmanager_back.po.User;

public interface IUserService {
    /**
     * 网页用户的user（用于网页下载app时的登记）
     * @return
     */
    User findWebUser();

    User findById(Integer id);


    DownUser saveDownUser(DownUser downUser);
}
