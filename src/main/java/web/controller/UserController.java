package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @GetMapping(value = "/users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "/users";
    }
    @GetMapping(value = "/{id}" )
    public String editUserId(@PathVariable("id") int id, Model model) {
        User user = userService.getById(id);
        return null;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(user);
        return modelAndView;
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
    }
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        return modelAndView;
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
