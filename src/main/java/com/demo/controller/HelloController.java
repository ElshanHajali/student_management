package com.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {

    @Value("${hello.msg}")
    private String fromProperties;

    @GetMapping("/hello-world")
    @ApiOperation(
            value = "Prints message ",
            notes = "No arg required",
            httpMethod = "GET"
    )
    public String helloWorld() {
        return fromProperties;
    }

}
