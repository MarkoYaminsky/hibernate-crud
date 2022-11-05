package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.dto.BankDto;
import com.yaminsky.bankspringhibernate.service.IBankService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/banks")
public class BankController {
    private IBankService bankService;

    @GetMapping(value = "/{bankId}")
    public ResponseEntity<BankDto> getBank(@PathVariable Integer bankId) {
        BankDto bankDto = bankService.findById(bankId);
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BankDto>> getAllBanks() {
        CollectionModel<BankDto> bankDto = bankService.findAll();
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BankDto> addBank(@RequestBody BankDto bank) {
        BankDto bankDto = bankService.create(bank);
        return new ResponseEntity<>(bankDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bankId}")
    public ResponseEntity<?> updateBank(@RequestBody BankDto newBank, @PathVariable Integer bankId) {
        bankService.update(bankId, newBank);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bankId}")
    public ResponseEntity<?> deleteBank(@PathVariable Integer bankId) {
        bankService.delete(bankId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
