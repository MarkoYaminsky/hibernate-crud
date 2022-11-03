package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.BankAccountController;
import com.yaminsky.bankspringhibernate.controller.BankController;
import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import com.yaminsky.bankspringhibernate.dto.BankDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BankDtoAssembler implements RepresentationModelAssembler<BankEntity, BankDto> {
    @Override
    public BankDto toModel(BankEntity bankEntity) {
        BankDto bankDto = BankDto.builder()
                .id(bankEntity.getId())
                .name(bankEntity.getName())
                .countryId(bankEntity.getCountryByCountryId().getId())
                .build();
        Link selfLink = linkTo(methodOn(BankController.class).getBank(bankDto.getId())).withSelfRel();
        bankDto.add(selfLink);
        return bankDto;
    }

    @Override
    public CollectionModel<BankDto> toCollectionModel(Iterable<? extends BankEntity> entities) {
        CollectionModel<BankDto> bankDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(BankController.class).getAllBanks()).withSelfRel();
        bankDto.add(selfLink);
        return bankDto;
    }
}
