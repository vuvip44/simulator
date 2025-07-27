package com.example.simulator.controller;

import com.example.simulator.dto.ApiResponse;
import com.example.simulator.exception.SimFile;
import com.example.simulator.model.Test;
import com.example.simulator.service.SimulatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/simulator", produces = MediaType.APPLICATION_JSON_VALUE)
public class SimulatorController {

    @Autowired
    private SimulatorService service;

    //Exceptor invalid
    @PostMapping("/success")
    @ResponseBody
    public ResponseEntity<String> success(@RequestBody @Valid Test test) {
        ApiResponse<Test> response = service.getTest(test);
        String dataJson = "{\"id\":" + response.getData().getId() + ",\"name\":\"" + response.getData().getName() + "\"}";
        return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\",\"data\":" + dataJson + "}");
    }

    @GetMapping("/timeout")
    @ResponseBody
    public ResponseEntity<String> timeout() {
        try {
            ApiResponse<String> response = service.timeout();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/timeout/{seconds}")
    @ResponseBody
    public ResponseEntity<String> timeoutConfigurable(@PathVariable int seconds) {
        try {
            ApiResponse<String> response = service.timeoutConfigurable(seconds);
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/timeout-random")
    @ResponseBody
    public ResponseEntity<String> timeoutRandom() {
        try {
            ApiResponse<String> response = service.timeoutRandom();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/error")
    @ResponseBody
    public ResponseEntity<String> error() {
        try {
            ApiResponse response = service.error();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/upload")
    @ResponseBody
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new SimFile("No file uploaded");
        }
        ApiResponse response = service.largeFile(file);
        return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
    }

    @PostMapping("/upload-json")
    @ResponseBody
    public ResponseEntity<String> uploadJson(@RequestBody String jsonData) {
        try {
            // Parse JSON data using Jackson
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            com.fasterxml.jackson.databind.JsonNode json = mapper.readTree(jsonData);
            
            String filename = json.get("filename").asText();
            String contentType = json.get("contentType").asText();
            long size = json.get("size").asLong();
            String base64Data = json.get("data").asText();
            
            // Convert base64 back to bytes
            byte[] fileData = java.util.Base64.getDecoder().decode(base64Data);
            
            // Check file size (2MB limit)
            if (fileData.length > 2 * 1024 * 1024) {
                return ResponseEntity.status(413).body("{\"status\":413,\"message\":\"File size too large. Maximum allowed size is 2MB\"}");
            }
            
            // Process the file data directly
            String result = "File uploaded successfully: " + filename + " (" + fileData.length + " bytes)";
            return ResponseEntity.ok("{\"status\":200,\"message\":\"" + result + "\"}");
            
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"Error processing file: " + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/flip")
    @ResponseBody
    public ResponseEntity<String> flip() {
        try {
            ApiResponse<String> response = service.flip();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    // Thêm các API endpoints cho TestCircuit
    @GetMapping("/api1")
    @ResponseBody
    public ResponseEntity<String> api1() {
        try {
            ApiResponse<String> response = service.api1();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api2")
    @ResponseBody
    public ResponseEntity<String> api2() {
        try {
            ApiResponse<String> response = service.api2();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api3")
    @ResponseBody
    public ResponseEntity<String> api3() {
        try {
            ApiResponse<String> response = service.api3();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api4")
    @ResponseBody
    public ResponseEntity<String> api4() {
        try {
            ApiResponse<String> response = service.api4();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

    @GetMapping("/api5")
    @ResponseBody
    public ResponseEntity<String> api5() {
        try {
            ApiResponse<String> response = service.api5();
            return ResponseEntity.ok("{\"status\":" + response.getStatus() + ",\"message\":\"" + response.getMessage() + "\"}");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("{\"status\":500,\"message\":\"" + e.getMessage() + "\"}");
        }
    }

}
