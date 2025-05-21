package com.example.expensemgmt.repository;

import com.example.expensemgmt.model.Expense;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExpenseRepository extends ReactiveMongoRepository<Expense, String> {
}
