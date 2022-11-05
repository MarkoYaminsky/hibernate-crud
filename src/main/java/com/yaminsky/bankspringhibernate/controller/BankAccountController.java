package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import com.yaminsky.bankspringhibernate.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/bankAccounts")
public class BankAccountController {
    private IBankAccountService bankAccountService;

    @GetMapping(value = "/{bankAccountId}")
    public ResponseEntity<BankAccountDto> getBankAccount(@PathVariable Integer bankAccountId) {
        BankAccountDto bankAccountDto = bankAccountService.findById(bankAccountId);
        return new ResponseEntity<>(bankAccountDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BankAccountDto>> getAllBankAccounts() {
        CollectionModel<BankAccountDto> bankAccountDto = bankAccountService.findAll();
        return new ResponseEntity<>(bankAccountDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BankAccountDto> addBankAccount(@RequestBody BankAccountDto bankAccount) {
        BankAccountDto bankAccountDto = bankAccountService.create(bankAccount);
        return new ResponseEntity<>(bankAccountDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bankAccountId}")
    public ResponseEntity<?> updateBankAccount(@RequestBody BankAccountDto newBankAccount, @PathVariable Integer bankAccountId) {
        bankAccountService.update(bankAccountId, newBankAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bankAccountId}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Integer bankAccountId) {
        bankAccountService.delete(bankAccountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
