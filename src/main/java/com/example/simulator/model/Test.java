package com.example.simulator.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Test {
    @NotNull(message = "ID không được bỏ trống")
    private int id;

    @NotBlank(message = "Name không được bỏ trống")
    private String name;

    // Manual getters and setters in case Lombok doesn't work
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
