package org.example.service;

import org.example.model.User;

import java.io.IOException;

public interface UserService {

    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user) throws IOException;
}
