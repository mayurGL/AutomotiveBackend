package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository class for user entity
 */
@Repository
public interface UserRepository extends CassandraRepository<User, String> {

    @AllowFiltering
    User findByUsernameAndPassword(String username, String password);
}
