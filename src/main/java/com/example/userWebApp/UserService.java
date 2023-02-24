package com.example.userWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public Page<User> getAllUser(int pageNumber, String sortField, String sortDir){
//        Sort sort = Sort.by("name").ascending();
//        sort = sort.equals("asc") ? sort.descending():sort.ascending();

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1,5,sort);
        return userDao.findAll(pageable);
    }
}
