package com.hexagrammers.DamPlay.Services;

import com.hexagrammers.DamPlay.Models.User;
import com.hexagrammers.DamPlay.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserManager {
    @Autowired
    UserRepository repository;

    public void createUser(User user)
    {
        repository.save(user);
    }

    public User findByEmail(String email)
    {
        return repository.findByEmail(email);
    }

    public User findById(int id)
    {
        return repository.findById(id);
    }

    public List<User> findAllUsers()
    {
        return (List<User>) repository.findAll();
    }
}
