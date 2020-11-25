package demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@Controller
public class UserController {

    @GetMapping("/")
    public String login() {
        return "index";
    }

    @GetMapping("/opret_projekt")
    public String createProject() {
     return "opret_projekt";
    }



}