package com.example.project_client.model;

import lombok.Data;


import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class Promotion {
    private Integer id = 0;
    private String information = "";
    private LocalDate startDate = LocalDate.now();
    private LocalDate endDate = LocalDate.now();
    private Boolean needCondition = false;
    private String name = "";
    private Map<Integer, Double> products = new HashMap<>();
}
