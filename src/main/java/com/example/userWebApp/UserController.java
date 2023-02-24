package com.example.userWebApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private  UserService userService;

    @GetMapping("/")
    public String home(Model model){
        return listByPage(model,1,"name","asc");
    }
    @GetMapping("/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") Integer currentPage,
                             @RequestParam("sortField") String sortField,
                             @RequestParam("sortDir") String sortDir){
        Page<User> page = userService.getAllUser(currentPage,sortField,sortDir);
        long totalItems= page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<User> list = page.getContent();
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("any",list);
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("checkDir",sortDir.equals("asc")?"desc":"asc");
        return "home";
    }

    @GetMapping("/add")
    public String addNewUser(){
        return "addnewuser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute User user){
        userDao.save(user);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, Model model){
       User returnedUser = userDao.findById(id).get();
       model.addAttribute("any",returnedUser);
        return "update";
    }
    @RequestMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id){
        userDao.delete(userDao.findById(id).get());
        return "redirect:/";
    }
}
