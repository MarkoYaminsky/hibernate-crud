package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import com.yaminsky.bankspringhibernate.dto.assembler.BankAccountDtoAssembler;
import com.yaminsky.bankspringhibernate.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/bankAccounts")
public class BankAccountController {
    private IBankAccountService bankAccountService;
    private BankAccountDtoAssembler bankAccountDtoAssembler;

    @GetMapping(value = "/{bankAccountId}")
    public ResponseEntity<BankAccountDto> getBankAccount(@PathVariable Integer bankAccountId) {
        BankAccountEntity bankAccount = bankAccountService.findById(bankAccountId);
        BankAccountDto bankAccountDto = bankAccountDtoAssembler.toModel(bankAccount);
        return new ResponseEntity<>(bankAccountDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BankAccountDto>> getAllBankAccounts() {
        List<BankAccountEntity> bankAccounts = bankAccountService.findAll();
        CollectionModel<BankAccountDto> bankAccountDto = bankAccountDtoAssembler.toCollectionModel(bankAccounts);
        return new ResponseEntity<>(bankAccountDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BankAccountDto> addBankAccount(@RequestBody BankAccountEntity bankAccount) {
        BankAccountEntity newBankAccount = bankAccountService.create(bankAccount);
        BankAccountDto bankAccountDto = bankAccountDtoAssembler.toModel(newBankAccount);
        return new ResponseEntity<>(bankAccountDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bankAccountId}")
    public ResponseEntity<?> updateBankAccount(@RequestBody BankAccountEntity newBankAccount, @PathVariable Integer bankAccountId) {
        bankAccountService.update(bankAccountId, newBankAccount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bankAccountId}")
    public ResponseEntity<?> deleteBankAccount(@PathVariable Integer bankAccountId) {
        bankAccountService.delete(bankAccountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
