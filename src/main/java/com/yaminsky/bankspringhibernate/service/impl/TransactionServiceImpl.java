package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.TransactionEntity;
import com.yaminsky.bankspringhibernate.exception.TransactionNotFoundException;
import com.yaminsky.bankspringhibernate.repository.ITransactionRepository;
import com.yaminsky.bankspringhibernate.service.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {
    private ITransactionRepository transactionRepository;

    @Override
    public List<TransactionEntity> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionEntity findById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id " + id + " not found"));
    }

    @Override
    @Transactional
    public TransactionEntity create(TransactionEntity transaction) {
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional
    public void update(Integer id, TransactionEntity newTransaction) {
        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id" + id + " not found"));
        transaction.setEvent(newTransaction.getEvent());
        transaction.setSumInDollars(newTransaction.getSumInDollars());
        transactionRepository.save(transaction);
    }

    @Override
    public void delete(Integer id) {
        TransactionEntity TransactionEntity = transactionRepository.findById(id)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction with id" + id + " not found"));
        transactionRepository.delete(TransactionEntity);
    }

    @Override
    public List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountBuyerId(BankAccountEntity bankAccountByBankAccountBuyerId) {
        return transactionRepository.getTransactionEntitiesByBankAccountByBankAccountBuyerId(bankAccountByBankAccountBuyerId);
    }

    @Override
    public List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountSellerId(BankAccountEntity bankAccountByBankAccount) {
        return transactionRepository.getTransactionEntitiesByBankAccountByBankAccountSellerId(bankAccountByBankAccount);
    }
}