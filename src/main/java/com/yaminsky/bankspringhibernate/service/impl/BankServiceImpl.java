package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.exception.BankNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IBankRepository;
import com.yaminsky.bankspringhibernate.service.IBankService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BankServiceImpl implements IBankService {
    private IBankRepository bankRepository;

    @Override
    public List<BankEntity> findAll() {
        return bankRepository.findAll();
    }

    @Override
    public BankEntity findById(Integer id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id " + id + " not found"));
    }

    @Override
    @Transactional
    public BankEntity create(BankEntity bank) {
        bankRepository.save(bank);
        return bank;
    }

    @Override
    @Transactional
    public void update(Integer id, BankEntity newBank) {
        BankEntity bank = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id" + id + " not found"));
        bank.setName(newBank.getName());
        bank.setCountryByCountryId(newBank.getCountryByCountryId());
        bankRepository.save(bank);
    }

    @Override
    public void delete(Integer id) {
        BankEntity bankEntity = bankRepository.findById(id)
                .orElseThrow(() -> new BankNotFoundException("Bank with id" + id + " not found"));
        bankRepository.delete(bankEntity);
    }

    @Override
    public List<BankEntity> getBankEntitiesByCountryByCountryId(CountryEntity countryByCountryId) {
        return bankRepository.getBankEntitiesByCountryByCountryId(countryByCountryId);
    }
}

