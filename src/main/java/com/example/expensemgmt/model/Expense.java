package com.example.expensemgmt.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "expenses")
public class Expense {
    @Id
    private String id;
    private String name;
    private String category;
    private Double amount;
    private LocalDate date;
}
