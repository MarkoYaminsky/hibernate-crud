package com.yaminsky.bankspringhibernate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "bankAccount", collectionRelation = "bankAccounts")
public class BankAccountDto extends RepresentationModel<BankAccountDto> {
    private final Integer id;
    private final String requisites;
    private final String personType;
    private final Integer bankId;
    private final Integer clientId;
}