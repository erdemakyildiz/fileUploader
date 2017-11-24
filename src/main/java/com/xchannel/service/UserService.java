package com.xchannel.service;

import com.xchannel.entity.User;

import java.util.List;

/**
 * Created by Detay on 24.11.2017.
 */
public interface UserService {

    public User saveUser(User user);

    public User getUser(String id);

    public boolean authUser(String id, String pw);

    public List<User> findAll();

}
