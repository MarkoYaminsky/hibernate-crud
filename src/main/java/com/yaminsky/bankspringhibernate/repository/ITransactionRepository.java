package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepository extends JpaRepository<TransactionEntity, Integer> {
    List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountBuyerId(BankAccountEntity bankAccountByBankAccountBuyerId);

    List<TransactionEntity> getTransactionEntitiesByBankAccountByBankAccountSellerId(BankAccountEntity bankAccountByBankAccountSellerId);
}
