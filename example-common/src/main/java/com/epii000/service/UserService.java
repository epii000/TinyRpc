package com.epii000.service;

import com.epii000.model.User;

import java.io.IOException;

public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user) throws IOException;

    default short getNumber() {
        return 1;
    }
}
