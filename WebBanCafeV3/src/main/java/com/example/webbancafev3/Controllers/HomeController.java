package com.example.webbancafev3.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController
{
    @GetMapping
    public String Index()
    {
        return "layout";
    }
    @GetMapping("/contact")
    public String Contact()
    {
        return "home/contact";
    }
    @GetMapping("/about")
    public String About()
    {
        return "home/about";
    }
    @GetMapping("/blog")
    public String Blog()
    {
        return "home/blog";
    }
    @GetMapping("/coffees")
    public String Coffees()
    {
        return "home/coffees";
    }
}
