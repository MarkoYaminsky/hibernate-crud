package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Data
@Table(name = "transaction", schema = "marko_yaminsky", catalog = "")
public class TransactionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "event")
    private String event;
    @Basic
    @Column(name = "sum_in_dollars")
    private BigDecimal sumInDollars;
    @ManyToOne
    @JoinColumn(name = "bank_account_seller_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity bankAccountByBankAccountSellerId;
    @ManyToOne
    @JoinColumn(name = "bank_account_buyer_id", referencedColumnName = "id", nullable = false)
    private BankAccountEntity bankAccountByBankAccountBuyerId;
}
