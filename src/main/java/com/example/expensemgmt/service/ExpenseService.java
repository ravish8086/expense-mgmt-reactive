package com.example.expensemgmt.service;

import com.example.expensemgmt.exception.ResourceNotFoundException;
import com.example.expensemgmt.model.Expense;
import com.example.expensemgmt.repository.ExpenseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;

    public Flux<Expense> findAll() {
        return this.repository.findAll();
    }

    public Mono<Expense> findById(String id) {
        return this.repository.findById(id).switchIfEmpty(Mono.error(new ResourceNotFoundException(
                "Expense with id " + id + " not found"
        )));
    }

    public Mono<Expense> save(Expense expense) {
        return this.repository.save(expense);
    }

    public Mono<Expense> update(Expense expense) {
        return this.repository.findById(expense.getId())
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Expense with id " + expense.getId() + " not found")))
                .flatMap(found -> {
                    expense.setId(found.getId());
                    return this.repository.save(expense);
                });
    }

    public Mono<Void> delete(String id) {
        return this.repository.deleteById(id)
                .switchIfEmpty(
                        Mono.error(
                                new ResourceNotFoundException("Expense with id " + id + " not found")));
    }

}
