package com.yaminsky.bankspringhibernate.dto.assembler;

import com.yaminsky.bankspringhibernate.domain.BankAccountEntity;
import com.yaminsky.bankspringhibernate.dto.BankAccountDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class BankAccountDtoAssembler implements RepresentationModelAssembler<BankAccountEntity, BankAccountDto> {
    @Override
    public BankAccountDto toModel(BankAccountEntity bankAccountEntity) {
        return BankAccountDto.builder()
                .id(bankAccountEntity.getId())
                .requisites(bankAccountEntity.getRequisites())
                .personType(bankAccountEntity.getPersonType())
                .clientId(bankAccountEntity.getClientByClientId().getId())
                .bankId(bankAccountEntity.getBankByBankId().getId())
                .build();
    }

    @Override
    public CollectionModel<BankAccountDto> toCollectionModel(Iterable<? extends BankAccountEntity> entities) {
        return RepresentationModelAssembler.super.toCollectionModel(entities);
    }
}
