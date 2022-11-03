package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankAccountRepository extends JpaRepository<BankAccountEntity, Integer> {
    List<BankAccountEntity> getBankAccountEntitiesByBankByBankId(BankEntity bankByBankId);

    List<BankAccountEntity> getBankAccountEntitiesByClientByClientId(ClientEntity clientByClientId);
}