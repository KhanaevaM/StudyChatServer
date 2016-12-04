package ru.levelp.dao;

import ru.levelp.User;

import java.util.List;

public interface UserDAO {
    String FIELD_EMAIL = "email";

    void add(User user);

    User get(long id);

    User getByEmail(String email);

    List<User> getAll();

    User delete(long id);
}