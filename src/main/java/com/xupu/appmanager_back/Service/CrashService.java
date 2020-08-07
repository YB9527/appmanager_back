package com.xupu.appmanager_back.Service;

import com.sun.org.apache.regexp.internal.RE;
import com.xupu.appmanager_back.po.Crash;
import com.xupu.appmanager_back.repository.CrashRepository;
import com.xupu.appmanager_back.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class CrashService implements ICrashService {
    @Autowired
    private CrashRepository crashRepository;

    @Override
    public List<Crash> findAll() {
        return crashRepository.findAll();
    }

    @Override
    public Crash save(Crash crash) {
        return crashRepository.save(crash);
    }


    @Transactional
    @Override
    public List<Crash> saveList(List<Crash> list) {
        if (Tool.isEmpty(list)) {
            return list;
        }
        for (int i = 0; i < list.size(); i++) {
            Crash crash = list.get(i);
            crash = save(crash);
            list.set(i, crash);
        }
        return list;
    }

    @Override
    public Crash findById(Integer id) {
        if (id == null) {
            return null;
        }
        return crashRepository.findById(id).get();
    }

    @Override
    public Crash flush(Crash crash) {
        Crash old = findById(crash.getId());
        if (old != null) {
            return crashRepository.saveAndFlush(crash);
        }
        return null;
    }


}
