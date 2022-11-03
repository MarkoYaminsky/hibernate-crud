package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.controller.CountryController;
import com.yaminsky.bankspringhibernate.controller.TransactionController;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.domain.TransactionEntity;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.dto.TransactionDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TransactionDtoAssembler implements RepresentationModelAssembler<TransactionEntity, TransactionDto> {
    @Override
    public TransactionDto toModel(TransactionEntity transactionEntity) {
        TransactionDto transactionDto = TransactionDto.builder()
                .id(transactionEntity.getId())
                .sumInDollars(transactionEntity.getSumInDollars())
                .event(transactionEntity.getEvent())
                .bankAccountBuyerId(transactionEntity.getBankAccountByBankAccountBuyerId().getId())
                .bankAccountSellerId(transactionEntity.getBankAccountByBankAccountSellerId().getId())
                .build();
        Link selfLink = linkTo(methodOn(TransactionController.class).getCountry(transactionDto.getId())).withSelfRel();
        transactionDto.add(selfLink);
        return transactionDto;
    }

    @Override
    public CollectionModel<TransactionDto> toCollectionModel(Iterable<? extends TransactionEntity> entities) {
        CollectionModel<TransactionDto> transactionDto = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(TransactionController.class).getAllTransactions()).withSelfRel();
        transactionDto.add(selfLink);
        return transactionDto;
    }
}
