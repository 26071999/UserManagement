package com.prakash.UserManagement.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

   @GetMapping("/users")
    public String userLists(Model model){
        List<User> listUsers = userService.findAllUsers();
        model.addAttribute("listUsers",listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public  String showUserForm(Model model){
       model.addAttribute("user",new User());
       model.addAttribute("pageTitle","Add New User");
       return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){

       userService.save(user);
       ra.addFlashAttribute("message","The new User has been added successfully!!!");
       return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id,Model model,RedirectAttributes ra){
        try {
            User user = userService.get(id);
            model.addAttribute("user",user);
            model.addAttribute("pageTitle","Edit User(Id="+id+")");
            return "user_form";
        } catch (UserNotFountException e) {
           ra.addFlashAttribute("message",e.getMessage());
           return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id,RedirectAttributes ra){
       try {
           userService.deleteById(id);
           ra.addFlashAttribute("message","The userID "+id+" had been deleted successfully");
       }
       catch (UserNotFountException u){
           ra.addFlashAttribute("message",u.getMessage());
       }

       return "redirect:/users";
    }

}
