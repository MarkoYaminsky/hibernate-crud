package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;

import java.util.List;

public interface IBankService extends IGeneralService<BankEntity, Integer> {
    List<BankEntity> getBankEntitiesByCountryByCountryId(CountryEntity countryByCountryId);
}
