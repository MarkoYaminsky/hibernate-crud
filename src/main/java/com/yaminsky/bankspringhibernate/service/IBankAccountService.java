package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import org.springframework.hateoas.CollectionModel;

public interface IBankAccountService extends IGeneralService<BankAccountDto, Integer> {
    CollectionModel<BankAccountDto> getBankAccountEntitiesByBankByBankId(Integer id);

    CollectionModel<BankAccountDto> getBankAccountEntitiesByClientByClientId(Integer id);

}
