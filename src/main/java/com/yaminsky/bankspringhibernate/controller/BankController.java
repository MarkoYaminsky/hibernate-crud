package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.dto.BankDto;
import com.yaminsky.bankspringhibernate.dto.assembler.BankDtoAssembler;
import com.yaminsky.bankspringhibernate.service.IBankService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/banks")
public class BankController {
    private IBankService bankService;
    private BankDtoAssembler bankDtoAssembler;

    @GetMapping(value = "/{bankId}")
    public ResponseEntity<BankDto> getBank(@PathVariable Integer bankId) {
        BankEntity bank = bankService.findById(bankId);
        BankDto bankDto = bankDtoAssembler.toModel(bank);
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<BankDto>> getAllBanks() {
        List<BankEntity> banks = bankService.findAll();
        CollectionModel<BankDto> bankDto = bankDtoAssembler.toCollectionModel(banks);
        return new ResponseEntity<>(bankDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<BankDto> addBank(@RequestBody BankEntity bank) {
        BankEntity newBank = bankService.create(bank);
        BankDto bankDto = bankDtoAssembler.toModel(newBank);
        return new ResponseEntity<>(bankDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bankId}")
    public ResponseEntity<?> updateBank(@RequestBody BankEntity newBank, @PathVariable Integer bankId) {
        bankService.update(bankId, newBank);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{bankId}")
    public ResponseEntity<?> deleteBank(@PathVariable Integer bankId) {
        bankService.delete(bankId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
