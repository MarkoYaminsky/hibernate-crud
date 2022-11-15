package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.CountryController;
import com.yaminsky.bankspringhibernate.domain.ContinentEntity;
import com.yaminsky.bankspringhibernate.dto.ContinentDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ContinentDtoAssembler implements RepresentationModelAssembler<ContinentEntity, ContinentDto> {
    @Override
    public ContinentDto toModel(ContinentEntity continentEntity) {
        return ContinentDto.builder()
                .id(continentEntity.getId())
                .name(continentEntity.getName())
                .build();
    }

    @Override
    public CollectionModel<ContinentDto> toCollectionModel(Iterable<? extends ContinentEntity> entities) {
        CollectionModel<ContinentDto> continentDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        continentDto.add(selfLink);
        return continentDto;
    }
}