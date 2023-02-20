package com.example.userWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Page<User> getAllUser(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber-1,5);
        return userDao.findAll(pageable);
    }
}
