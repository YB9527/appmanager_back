package com.xupu.appmanager_back.repository;

import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.po.DownUser;
import com.xupu.appmanager_back.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DownUserRepository extends JpaRepository<DownUser, Integer> {
    DownUser findByAppversionAndUser(AppVersion appVersion, User user);
}
