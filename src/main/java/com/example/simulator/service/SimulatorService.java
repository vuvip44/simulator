package com.example.simulator.service;

import com.example.simulator.dto.ApiResponse;
import com.example.simulator.exception.SImRuntime;
import com.example.simulator.exception.SimFile;
import com.example.simulator.model.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SimulatorService {
    private int flipCounter = 0;

    public ApiResponse<String> flip() {
        flipCounter++;
        if (flipCounter <= 3) {
            return new ApiResponse<>(200, "Flip: Success #" + flipCounter);
        } else {
            throw new SImRuntime("Flip: Simulated failure after 3 attempts");
        }
    }

    // Optional: reset lại
    public void resetFlip() {
        flipCounter = 0;
    }

    public ApiResponse<Test> getTest(Test test){
        return new ApiResponse<>(200,"Ok",test);
    }

    public ApiResponse<String> timeout() {
        try {
            // Simulate exact timeout - sleep longer than TestCircuit's timeout (15s)
            Thread.sleep(16000); // 16 seconds to ensure timeout
            return new ApiResponse<>(200, "Timeout should not reach here");
        } catch (InterruptedException e) {
            throw new SImRuntime("Timeout interrupted");
        }
    }

    public ApiResponse<String> timeoutConfigurable(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
            return new ApiResponse<>(200, "Timeout after " + seconds + " seconds");
        } catch (InterruptedException e) {
            throw new SImRuntime("Timeout interrupted");
        }
    }

    public ApiResponse<String> timeoutRandom() {
        try {
            // Random timeout between 5-20 seconds
            int randomSeconds = 5 + (int)(Math.random() * 15);
            Thread.sleep(randomSeconds * 1000L);
            return new ApiResponse<>(200, "Timeout after " + randomSeconds + " seconds");
        } catch (InterruptedException e) {
            throw new SImRuntime("Timeout interrupted");
        }
    }

    public ApiResponse error(){
        throw new SImRuntime("Internal Server Error");
    }

    public ApiResponse largeFile(MultipartFile file){
        long maxSize=2*1024*1024;
        if(file.getSize()>maxSize){
            throw new SimFile("File is too large");
        }
        return new ApiResponse(200,"File access");
    }

    // Thêm các API methods cho TestCircuit
    public ApiResponse<String> api1() {
        // Simulate success 80% of the time
        if (Math.random() < 0.8) {
            return new ApiResponse<>(200, "API1: Success");
        } else {
            throw new SImRuntime("API1: Simulated failure");
        }
    }

    public ApiResponse<String> api2() {
        // Simulate timeout sometimes
        if (Math.random() < 0.7) {
            return new ApiResponse<>(200, "API2: Success");
        } else {
            try {
                Thread.sleep(3000); // Simulate timeout
                return new ApiResponse<>(200, "API2: Success after delay");
            } catch (InterruptedException e) {
                throw new SImRuntime("API2: Timeout");
            }
        }
    }

    public ApiResponse<String> api3() {
        // Simulate error 30% of the time
        if (Math.random() < 0.7) {
            return new ApiResponse<>(200, "API3: Success");
        } else {
            throw new SImRuntime("API3: Simulated error");
        }
    }

    public ApiResponse<String> api4() {
        // Always success
        return new ApiResponse<>(200, "API4: Success");
    }

    public ApiResponse<String> api5() {
        // Simulate intermittent failures
        if (Math.random() < 0.6) {
            return new ApiResponse<>(200, "API5: Success");
        } else {
            throw new SImRuntime("API5: Simulated failure");
        }
    }


}
