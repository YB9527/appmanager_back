package com.xupu.appmanager_back.repository;

import com.xupu.appmanager_back.po.Crash;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrashRepository  extends JpaRepository<Crash, Integer> {

}