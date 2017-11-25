package com.xchannel.service.impl;

import com.xchannel.entity.User;
import com.xchannel.repository.UserRepository;
import com.xchannel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by Detay on 24.11.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUser(String id) {
        User user = null;
        if (!StringUtils.isEmpty(id)) {
            user = userRepository.findOne(id);

            if (user == null) {
                user = userRepository.findFirstByEmailEquals(id);
            }

            if (user == null) {
                user = userRepository.findFirstByUsernameEquals(id);
            }
        }

        return user;
    }

    @Override
    public boolean authUser(String id, String pw) {
        User user = null;
        if ((user = getUser(id)) != null){
            if (user.getPassword().equalsIgnoreCase(pw)){
                return true;
            }
        }

        return false;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
