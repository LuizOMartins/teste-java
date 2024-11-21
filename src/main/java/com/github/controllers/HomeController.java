package com.github.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/gerenciamentoMembros")
    public String gerenciamentoMembros() {
        return "gerenciamentoMembros";
    }

    @GetMapping("/formularioMembro")
    public String formularioMembro() {
        return "formularioMembro";
    }

}
