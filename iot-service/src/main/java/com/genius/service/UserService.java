package com.genius.service;

import com.genius.pojo.Users;
import com.genius.pojo.bo.UserBO;

public interface UserService {

    /**
     * 判断用户名是否存在
     * @return
     */
    public boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     * @param userBO
     * @return
     */
    public Users createUser(UserBO userBO);

    /**
     * 检索用户名和密码是否匹配，用于登录
     * @param username
     * @param password
     * @return
     */
    public Users queryUserForLogin(String username, String password);
}
