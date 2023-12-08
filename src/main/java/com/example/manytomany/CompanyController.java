package com.example.manytomany;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("test")
class CompanyController {

    @GetMapping("")
    public Optional<String> tt(@RequestParam(value = "a", required = false) Optional<String> a) {
        return a;
    }
}
