package com.example.expensemgmt.controller;

import com.example.expensemgmt.model.Expense;
import com.example.expensemgmt.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/expenses")
@AllArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping
    public Flux<Expense> getAllExpenses() {
        return expenseService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Expense> getExpenseById(@PathVariable String id) {
        return expenseService.findById(id);
    }

    @PostMapping
    public Mono<Expense> createExpense(@RequestBody Expense expense) {
        return expenseService.save(expense);
    }

    @PutMapping("{id}")
    public Mono<Expense> updateExpense(@PathVariable String id, @RequestBody Expense expense) {
        return expenseService.update(expense);
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteExpense(@PathVariable String id) {
        return expenseService.delete(id);
    }

}
