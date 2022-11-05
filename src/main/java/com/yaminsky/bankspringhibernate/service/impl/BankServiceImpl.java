package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.BankDto;
import com.yaminsky.bankspringhibernate.dto.assembler.BankDtoAssembler;
import com.yaminsky.bankspringhibernate.exception.BankNotFoundException;
import com.yaminsky.bankspringhibernate.exception.CountryNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IBankRepository;
import com.yaminsky.bankspringhibernate.repository.ICountryRepository;
import com.yaminsky.bankspringhibernate.service.IBankService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements IBankService {
    private IBankRepository bankRepository;
    private ICountryRepository countryRepository;
    private BankDtoAssembler bankDtoAssembler;

    @Override
    public CollectionModel<BankDto> findAll() {
        List<BankEntity> banks = bankRepository.findAll();
        return bankDtoAssembler.toCollectionModel(banks);
    }

    @Override
    public BankDto findById(Integer id) {
        BankEntity bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id " + id + " not found"));
        return bankDtoAssembler.toModel(bank);
    }

    @Override
    @Transactional
    public BankDto create(BankDto bank) {
        BankEntity bankEntity = new BankEntity();
        CountryEntity country = countryRepository.findById(bank.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        bankEntity.setCountryByCountryId(country);
        bankEntity.setName(bank.getName());
        return bank;
    }

    @Override
    @Transactional
    public void update(Integer id, BankDto newBank) {
        BankEntity bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id" + id + " not found"));
        bank.setName(newBank.getName());
        bank.setCountryByCountryId(countryRepository.findById(newBank.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country not found")));
        bankRepository.save(bank);
    }

    @Override
    public void delete(Integer id) {
        BankEntity bankEntity = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id" + id + " not found"));
        bankRepository.delete(bankEntity);
    }

    @Override
    public CollectionModel<BankDto> getBankEntitiesByCountryByCountryId(Integer id) {
        CountryEntity country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        List<BankEntity> banks = bankRepository.getBankEntitiesByCountryByCountryId(country);
        return bankDtoAssembler.toCollectionModel(banks);
    }
}

