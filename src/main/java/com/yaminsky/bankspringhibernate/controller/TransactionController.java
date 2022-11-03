package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.domain.TransactionEntity;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.dto.TransactionDto;
import com.yaminsky.bankspringhibernate.dto.assembler.TransactionDtoAssembler;
import com.yaminsky.bankspringhibernate.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/transactions")
public class TransactionController {
    private ITransactionService transactionService;
    private TransactionDtoAssembler transactionDtoAssembler;

    @GetMapping(value = "/{transactionId}")
    public ResponseEntity<TransactionDto> getCountry(@PathVariable Integer transactionId) {
        TransactionEntity country = transactionService.findById(transactionId);
        TransactionDto countryDto = transactionDtoAssembler.toModel(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<TransactionDto>> getAllTransactions() {
        List<TransactionEntity> countries = transactionService.findAll();
        CollectionModel<TransactionDto> countryDto = transactionDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TransactionDto> addCountry(@RequestBody TransactionEntity transaction) {
        TransactionEntity newTransaction = transactionService.create(transaction);
        TransactionDto transactionDto = transactionDtoAssembler.toModel(newTransaction);
        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{transactionId}")
    public ResponseEntity<?> updateTransaction(@RequestBody TransactionEntity newTransaction, @PathVariable Integer transactionId) {
        transactionService.update(transactionId, newTransaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{transactionId}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Integer transactionId) {
        transactionService.delete(transactionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
