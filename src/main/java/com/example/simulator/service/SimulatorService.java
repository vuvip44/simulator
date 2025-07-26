package com.example.simulator.service;

import com.example.simulator.dto.ApiResponse;
import com.example.simulator.exception.SImRuntime;
import com.example.simulator.exception.SimFile;
import com.example.simulator.model.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.concurrent.TimeoutException;

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

    public ApiResponse<String> timeout()throws InterruptedException{
        Thread.sleep(10000);
        return new ApiResponse<>(504,"Timeout");
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


}
