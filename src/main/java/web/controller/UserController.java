package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import javax.transaction.Transactional;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/users";
    }

    @GetMapping(value = "/{id}" )
    public String findUserById(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userService.getById(id));
        return "/get";
    }


    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());

        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";

    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getById(id));
        return "/edit";
    }


    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.edit(id, user);
        return "redirect:/users";

    }



    @DeleteMapping ("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }


}
