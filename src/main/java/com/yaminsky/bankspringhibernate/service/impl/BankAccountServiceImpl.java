package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import com.yaminsky.bankspringhibernate.dto.assembler.BankAccountDtoAssembler;
import com.yaminsky.bankspringhibernate.exception.BankAccountNotFoundException;
import com.yaminsky.bankspringhibernate.exception.BankNotFoundException;
import com.yaminsky.bankspringhibernate.exception.ClientNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IBankAccountRepository;
import com.yaminsky.bankspringhibernate.repository.IBankRepository;
import com.yaminsky.bankspringhibernate.repository.IClientRepository;
import com.yaminsky.bankspringhibernate.service.IBankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class BankAccountServiceImpl implements IBankAccountService {
    private IBankAccountRepository bankAccountRepository;
    private IBankRepository bankRepository;
    private IClientRepository clientRepository;
    private BankAccountDtoAssembler bankAccountDtoAssembler;

    @Override
    public CollectionModel<BankAccountDto> getBankAccountEntitiesByBankByBankId(Integer id) {
        BankEntity bank = bankRepository.findById(id).orElseThrow(() -> new BankNotFoundException("Bank not found"));
        List<BankAccountEntity> banksAccounts = bankAccountRepository.getBankAccountEntitiesByBankByBankId(bank);
        return bankAccountDtoAssembler.toCollectionModel(banksAccounts);
    }

    @Override
    public CollectionModel<BankAccountDto> getBankAccountEntitiesByClientByClientId(Integer id) {
        ClientEntity client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        List<BankAccountEntity> banksAccounts = bankAccountRepository.getBankAccountEntitiesByClientByClientId(client);
        return bankAccountDtoAssembler.toCollectionModel(banksAccounts);
    }

    @Override
    public BigDecimal getBankAccountMaximumBalance() {
        return bankAccountRepository.getBankAccountMaximumBalance();
    }

    @Override
    public CollectionModel<BankAccountDto> findAll() {
        List<BankAccountEntity> bankAccounts = bankAccountRepository.findAll();
        return bankAccountDtoAssembler.toCollectionModel(bankAccounts);
    }

    @Override
    public BankAccountDto findById(Integer id) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id " + id + " not found"));
        return bankAccountDtoAssembler.toModel(bankAccountEntity);
    }

    @Override
    @Transactional
    public BankAccountDto create(BankAccountDto bankAccount) {
        BankAccountEntity bankAccountEntity = new BankAccountEntity();
        BankEntity bank = bankRepository.findById(bankAccount.getBankId()).orElseThrow(() -> new BankNotFoundException("Bank not found"));
        ClientEntity client = clientRepository.findById(bankAccount.getClientId()).orElseThrow(() -> new ClientNotFoundException("Client not found"));
        bankAccountEntity.setBankByBankId(bank);
        bankAccountEntity.setClientByClientId(client);
        bankAccountEntity.setRequisites(bankAccount.getRequisites());
        bankAccountEntity.setPersonType(bankAccount.getPersonType());
        bankAccountEntity.setBalance(bankAccount.getBalance());
        bankAccountRepository.save(bankAccountEntity);
        return bankAccount;
    }

    @Override
    @Transactional
    public void update(Integer id, BankAccountDto newBankAccount) {
        BankAccountEntity bankAccount = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id" + id + " not found"));
        bankAccount.setRequisites(newBankAccount.getRequisites());
        bankAccount.setPersonType(newBankAccount.getPersonType());
        bankAccount.setClientByClientId(clientRepository.findById(newBankAccount.getId()).orElseThrow(() -> new ClientNotFoundException("Client not found")));
        bankAccount.setBankByBankId(bankRepository.findById(newBankAccount.getBankId()).orElseThrow(() -> new BankNotFoundException("Bank not found")));
        bankAccountRepository.save(bankAccount);
    }

    @Override
    public void delete(Integer id) {
        BankAccountEntity bankAccountEntity = bankAccountRepository.findById(id)
                .orElseThrow(() -> new BankAccountNotFoundException("Bank account with id" + id + " not found"));
        bankAccountRepository.delete(bankAccountEntity);
    }
}
