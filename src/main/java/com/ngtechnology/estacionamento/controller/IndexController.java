package com.ngtechnology.estacionamento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
 /** não precisa dessa controller **/
    @GetMapping()
    public  String get() { return  "API de controle de vagas";}
}
