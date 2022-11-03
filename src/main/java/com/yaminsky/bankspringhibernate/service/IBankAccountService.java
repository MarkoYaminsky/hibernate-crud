package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.ClientEntity;

import javax.transaction.Transactional;
import java.util.List;

public interface IBankAccountService extends IGeneralService<BankAccountEntity, Integer> {
    List<BankAccountEntity> getBankAccountEntitiesByBankByBankId(BankEntity bankByBankId);

    List<BankAccountEntity> getBankAccountEntitiesByClientByClientId(ClientEntity clientByClientId);

}
