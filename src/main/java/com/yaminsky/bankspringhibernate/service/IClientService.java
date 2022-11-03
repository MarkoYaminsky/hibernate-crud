package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;

import java.util.List;

public interface IClientService extends IGeneralService<ClientEntity, Integer> {
    List<ClientEntity> getClientEntitiesByCountryByCountryId(CountryEntity countryByCountryId);
}
