package com.xupu.appmanager_back.Service;

import com.sun.org.apache.regexp.internal.RE;
import com.xupu.appmanager_back.po.AppVersion;
import com.xupu.appmanager_back.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService  implements IAppService {

    @Autowired
    private AppRepository appRepository;
    @Override
    public AppVersion save(AppVersion appVersion) {
      return   appRepository.save(appVersion);
    }

    @Override
    public List<AppVersion> findAll() {
        return appRepository.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        appRepository.deleteById(id);
    }

    @Override
    public AppVersion findById(Integer id) {
        if(id == null){
            return null;
        }
        AppVersion appVersion =  appRepository.findById(id).get();
        return appVersion;
    }

    @Override
    public AppVersion findNewsApp() {
        List<AppVersion> list = findAll();
        if(list.size() > 0){
            return  list.get(list.size()-1);
        }
        return null;
    }

    @Override
    public AppVersion flush(AppVersion appVersion) {
        AppVersion old = findById(appVersion.getId());
        if(old != null){
            return  appRepository.saveAndFlush(appVersion);

        }
        return null;
    }
}
