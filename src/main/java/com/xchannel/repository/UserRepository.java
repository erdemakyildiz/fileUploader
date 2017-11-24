package com.xchannel.repository;

import com.xchannel.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Detay on 24.11.2017.
 */
public interface UserRepository extends MongoRepository<User, String> {

    public User findFirstByUsernameEquals(String username);
    public User findFirstByEmailEquals(String email);

}
