package com.example.webbancafev3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController
{
    @GetMapping
    public String Index()
    {
        return "layout.html";
    }
}
