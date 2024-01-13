package com.example.project_client.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class OrderBill {
    private String id = "";
    private String buyDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    private String customerPhoneNumber = "";
    private String userStaffId = "";
    private Integer total = 0;
    private Integer received = 0;
    private Integer changeMoney = 0;
    private Boolean payMethod = true;
    private Integer deduction = 0;
    private Integer original = 0;
    private String promotion = "";
    private List<Map<String, Object>> products = new ArrayList<>();
}
