package com.example.simulator.controller;

import com.example.simulator.dto.ApiResponse;
import com.example.simulator.model.Test;
import com.example.simulator.service.SimulatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/simulator")
public class SimulatorController {

    @Autowired
    private SimulatorService service;

    //Exceptor invalid
    @PostMapping("/success")
    public ApiResponse<Test> success(@RequestBody @Valid Test test) {
        return service.getTest(test);
    }

    @GetMapping("/timeout")
    public ApiResponse<String> timeout() throws InterruptedException {
        return service.timeout();
    }

    @GetMapping("/error")
    public ApiResponse error() {
        return service.error();
    }

    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("file") MultipartFile file) {
        return service.largeFile(file);
    }
}
