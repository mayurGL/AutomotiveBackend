package com.global.automotivebackend.repository;

import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CassandraRepository <User, Integer>{
   // public User findByUsername(String username);

    @AllowFiltering
    public User findByUsernameAndPassword(String username, String password);
}
