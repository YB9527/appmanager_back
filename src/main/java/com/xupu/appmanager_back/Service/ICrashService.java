package com.xupu.appmanager_back.Service;

import com.xupu.appmanager_back.po.Crash;

import java.util.List;

public interface ICrashService {
    List<Crash> findAll();

    Crash save(Crash stringToObject);

    List<Crash> saveList(List<Crash> list);

    Crash findById(Integer id);
    Crash flush(Crash crash);
}
