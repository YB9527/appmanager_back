package com.xupu.appmanager_back.Service;

import com.xupu.appmanager_back.po.DownUser;
import com.xupu.appmanager_back.po.User;
import com.xupu.appmanager_back.repository.DownUserRepository;
import com.xupu.appmanager_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DownUserRepository downUserRepository;

    private  String USER_WEB_NAME="__网页";
    private User webUser;


    @Override
    public User findWebUser() {
        if(webUser == null){
            webUser = userRepository.findUsersByUserName(USER_WEB_NAME);
            if(webUser == null){
                webUser = new User();
                webUser.setUserName(USER_WEB_NAME);
                webUser.setLoginName(USER_WEB_NAME);
                userRepository.save(webUser);
            }
        }
        return webUser;
    }

    @Override
    public User findById(Integer id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public DownUser saveDownUser(DownUser downUser) {
        return downUserRepository.save(downUser);
    }
}
