package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.CountryController;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CountryDtoAssembler implements RepresentationModelAssembler<CountryEntity, CountryDto> {
    @Override
    public CountryDto toModel(CountryEntity clientEntity) {
        return CountryDto.builder()
                .id(clientEntity.getId())
                .name(clientEntity.getName())
                .build();
    }

    @Override
    public CollectionModel<CountryDto> toCollectionModel(Iterable<? extends CountryEntity> entities) {
        CollectionModel<CountryDto> countryDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CountryController.class).getAllCountries()).withSelfRel();
        countryDto.add(selfLink);
        return countryDto;
    }
}