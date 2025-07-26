package com.example.simulator.controller;

import com.example.simulator.dto.ApiResponse;
import com.example.simulator.exception.SimFile;
import com.example.simulator.model.Test;
import com.example.simulator.service.SimulatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.CompletableFuture;

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
    public CompletableFuture<ApiResponse<String>> timeout() {
        return service.timeout();
    }

    @GetMapping("/error")
    public ApiResponse error() {
        return service.error();
    }

    @PostMapping("/upload")
    public ApiResponse upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new SimFile("No file uploaded");
        }
        return service.largeFile(file);
    }

    @GetMapping("/flip")
    public ApiResponse<String> flip() {
        return service.flip();
    }

}
