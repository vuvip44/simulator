package com.example.simulator.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Test {
    @NotBlank(message = "Không được bỏ trống")
    private int id;

    @NotBlank(message = "Không bỏ trống")
    private String name;

}
