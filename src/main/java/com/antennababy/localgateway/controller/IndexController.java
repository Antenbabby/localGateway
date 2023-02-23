package com.antennababy.localgateway.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/")
@Controller
public class IndexController {
    @RequestMapping
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect("/static/index.html");
    }
}
