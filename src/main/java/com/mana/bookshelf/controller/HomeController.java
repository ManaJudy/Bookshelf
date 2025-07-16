package com.mana.bookshelf.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping
    public String home(Model model, HttpSession session) {
        String message = session.getAttribute("adminId") != null ? "Welcome, Admin!" : "Welcome to the Book Shelf!";
        model.addAttribute("message", message);
        return "home";
    }
}
