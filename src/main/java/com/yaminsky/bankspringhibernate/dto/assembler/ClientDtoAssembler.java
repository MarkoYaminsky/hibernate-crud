package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<ClientEntity, ClientDto> {
    @Override
    public ClientDto toModel(ClientEntity clientEntity) {
        return ClientDto.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .countryId(clientEntity.getCountryByCountryId().getId())
                .build();
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends ClientEntity> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}