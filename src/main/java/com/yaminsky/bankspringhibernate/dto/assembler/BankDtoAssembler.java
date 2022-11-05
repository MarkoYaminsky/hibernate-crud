package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.dto.BankDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BankDtoAssembler implements RepresentationModelAssembler<BankEntity, BankDto> {
    @Override
    public BankDto toModel(BankEntity bankEntity) {
        return BankDto.builder()
                .id(bankEntity.getId())
                .name(bankEntity.getName())
                .countryId(bankEntity.getCountryByCountryId().getId())
                .build();
    }

    @Override
    public CollectionModel<BankDto> toCollectionModel(Iterable<? extends BankEntity> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
