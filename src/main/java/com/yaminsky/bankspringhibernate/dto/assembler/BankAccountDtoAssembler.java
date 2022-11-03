package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.BankAccountController;
import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BankAccountDtoAssembler implements RepresentationModelAssembler<BankAccountEntity, BankAccountDto> {
    @Override
    public BankAccountDto toModel(BankAccountEntity bankAccountEntity) {
        BankAccountDto bankDto = BankAccountDto.builder()
                .id(bankAccountEntity.getId())
                .requisites(bankAccountEntity.getRequisites())
                .personType(bankAccountEntity.getPersonType())
                .clientId(bankAccountEntity.getClientByClientId().getId())
                .bankId(bankAccountEntity.getBankByBankId().getId())
                .build();
        Link selfLink = linkTo(methodOn(BankAccountController.class).getBankAccount(bankDto.getId())).withSelfRel();
        bankDto.add(selfLink);
        return bankDto;
    }

    @Override
    public CollectionModel<BankAccountDto> toCollectionModel(Iterable<? extends BankAccountEntity> entities) {
        CollectionModel<BankAccountDto> bankAccountDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(BankAccountController.class).getAllBankAccounts()).withSelfRel();
        bankAccountDto.add(selfLink);
        return bankAccountDto;
    }
}
