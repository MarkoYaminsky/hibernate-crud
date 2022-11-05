package com.yaminsky.bankspringhibernate.service;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;

public interface IClientService extends IGeneralService<ClientDto, Integer> {
    CollectionModel<ClientDto> getClientEntitiesByCountryByCountryId(Integer id);
}
