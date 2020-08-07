package com.xupu.appmanager_back.repository;

import com.xupu.appmanager_back.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
     User findUsersByUserName(String username);
}
