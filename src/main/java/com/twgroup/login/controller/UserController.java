package com.twgroup.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {
    @GetMapping("/blog/index")
    public String index() {
        return "html/index.html";
    }
    @GetMapping("/blog/login")
    public String login() {
        return "html/login.html";
    }
    @GetMapping("/blog/join")
    public String join() {
        return "html/join.html";
    }
}
