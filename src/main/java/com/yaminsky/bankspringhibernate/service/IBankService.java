package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.dto.BankDto;
import org.springframework.hateoas.CollectionModel;

public interface IBankService extends IGeneralService<BankDto, Integer> {
    CollectionModel<BankDto> getBankEntitiesByCountryByCountryId(Integer id);
}
