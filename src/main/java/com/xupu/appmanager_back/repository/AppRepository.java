package com.xupu.appmanager_back.repository;

import com.xupu.appmanager_back.po.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository  extends JpaRepository<AppVersion, Integer> {

        }
