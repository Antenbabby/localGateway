package com.antennababy.localgateway.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/")
@Controller
@Slf4j
public class IndexController {
    @Value("${server.servlet.context-path}")
    private String contextPath;
    @RequestMapping
    public void index(HttpServletResponse response) throws IOException {
        response.sendRedirect(contextPath+"/static/index.html");
    }

    @RequestMapping("/{fileName}")
    public void page(@PathVariable String fileName,HttpServletResponse response) throws IOException {
        response.sendRedirect(contextPath+"/static/index.html?file="+fileName);
    }
}
