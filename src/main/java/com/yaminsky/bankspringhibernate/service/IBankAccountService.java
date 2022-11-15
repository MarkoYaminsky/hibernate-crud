package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import org.springframework.hateoas.CollectionModel;

import java.math.BigDecimal;

public interface IBankAccountService extends IGeneralService<BankAccountDto, Integer> {
    CollectionModel<BankAccountDto> getBankAccountEntitiesByBankByBankId(Integer id);
    CollectionModel<BankAccountDto> getBankAccountEntitiesByClientByClientId(Integer id);

    BigDecimal getBankAccountMaximumBalance();
}
