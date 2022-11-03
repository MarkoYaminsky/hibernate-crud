package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.TransactionEntity;

import java.util.List;

public interface ITransactionService extends IGeneralService<TransactionEntity, Integer> {
    List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountBuyerId(BankAccountEntity bankAccountByBankAccountBuyerId);

    List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountSellerId(BankAccountEntity bankAccountByBankAccount);
}
