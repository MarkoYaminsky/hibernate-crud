package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.exception.BankAccountNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IBankAccountRepository;
import com.yaminsky.bankspringhibernate.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private IBankAccountRepository bankAccountRepository;

    @Override
    public List<BankAccountEntity> getBankAccountEntitiesByBankByBankId(BankEntity bankByBankId) {
        return bankAccountRepository.getBankAccountEntitiesByBankByBankId(bankByBankId);
    }

    @Override
    public List<BankAccountEntity> getBankAccountEntitiesByClientByClientId(ClientEntity clientByClientId) {
        return bankAccountRepository.getBankAccountEntitiesByClientByClientId(clientByClientId);
    }

    @Override
    public List<BankAccountEntity> findAll() {
        return bankAccountRepository.findAll();
    }

    @Override
    public BankAccountEntity findById(Integer id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id " + id + " not found"));
    }

    @Override
    @Transactional
    public BankAccountEntity create(BankAccountEntity bankAccount) {
        bankAccountRepository.save(bankAccount);
        return bankAccount;
    }

    @Override
    @Transactional
    public void update(Integer id, BankAccountEntity newBankAccount) {
        BankAccountEntity bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id" + id + " not found"));
        bankAccount.setRequisites(newBankAccount.getRequisites());
        bankAccount.setPersonType(newBankAccount.getPersonType());
        bankAccount.setClientByClientId(newBankAccount.getClientByClientId());
        bankAccount.setBankByBankId(newBankAccount.getBankByBankId());
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(Integer id) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id" + id + " not found"));
        bankAccountRepository.delete(bankAccountEntity);
    }
}
