package br.com.fullstack.education.m1s12.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("home")
public class HomeController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello world!"); // Retorna 200
    }

}
