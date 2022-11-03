package com.yaminsky.bankspringhibernate.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "transaction", collectionRelation = "transactions")
public class TransactionDto extends RepresentationModel<TransactionDto> {
    private Integer id;
    private String event;
    private BigDecimal sumInDollars;
    private Integer bankAccountBuyerId;
    private Integer bankAccountSellerId;
}
