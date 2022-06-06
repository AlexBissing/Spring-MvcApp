package com.bissing.service;

import com.bissing.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
