package com.bissing.dao;

import com.bissing.model.User;

import java.util.List;

public interface UserDAO {
    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
