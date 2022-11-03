package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.ClientController;
import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.dto.ClientDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<ClientEntity, ClientDto> {
    @Override
    public ClientDto toModel(ClientEntity clientEntity) {
        ClientDto clientDto = ClientDto.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .countryId(clientEntity.getCountryByCountryId().getId())
                .build();
        Link selfLink = linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends ClientEntity> entities) {
        CollectionModel<ClientDto> clientDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDto.add(selfLink);
        return clientDto;
    }
}