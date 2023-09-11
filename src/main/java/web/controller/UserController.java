package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

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

    @GetMapping("/users/delete")
    public String deleteUser(@RequestParam(value = "id", required = false) Integer id,
                             Model model) {
        User user = userService.getById(id);
        userService.delete(user);
        model.addAttribute("user", user);
        return "/users";
    }


}
