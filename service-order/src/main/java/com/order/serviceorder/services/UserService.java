package com.order.serviceorder.services;

import com.order.serviceorder.entities.UserEntity;
import com.order.serviceorder.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public void createUser(UserEntity user) {
        repository.save(user);
    }
}
