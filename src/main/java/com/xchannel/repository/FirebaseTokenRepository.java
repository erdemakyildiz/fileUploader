package com.xchannel.repository;

import com.xchannel.entity.FirebaseToken;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Detay on 23.11.2017.
 */
public interface FirebaseTokenRepository extends MongoRepository<FirebaseToken, String> {

    public FirebaseToken findFirstByFirebaseTokenEquals(String token);

}
