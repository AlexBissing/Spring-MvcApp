package com.bissing;

import com.bissing.dao.UserDAO;
import com.bissing.dao.impl.JdbcTemplateUserDAO;
import com.bissing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class  MainController {

    @Autowired
    @Qualifier("jdbcApiUserDAO")
    private UserDAO userDAO;


    @GetMapping("/view/{name}")
    public String view(@PathVariable("name") String name,  Model model) {
        model.addAttribute("msg", "Hello " + name);
        return "index";
    }

    @GetMapping("/raw")
    @ResponseBody
    public String raw() {
        return "Raw data";
    }

    @GetMapping("/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userDAO.getAll());
        return "/users";
    }

    @GetMapping("/users/new")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/sign_up";
    }

    @PostMapping("/users/new")
    public String signUp(@ModelAttribute User user) {
        userDAO.add(user);
        return "redirect:/users";
    }
}
