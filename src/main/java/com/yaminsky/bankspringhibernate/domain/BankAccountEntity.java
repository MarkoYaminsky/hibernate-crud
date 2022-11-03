package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "bank_account", schema = "marko_yaminsky", catalog = "")
public class BankAccountEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "requisites")
    private String requisites;
    @Basic
    @Column(name = "person_type")
    private String personType;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientByClientId;
    @ManyToOne
    @JoinColumn(name = "bank_id", referencedColumnName = "id", nullable = false)
    private BankEntity bankByBankId;
    @OneToMany(mappedBy = "bankAccountByBankAccountSellerId")
    private List<TransactionEntity> transactionsBySellerId;
    @OneToMany(mappedBy = "bankAccountByBankAccountBuyerId")
    private List<TransactionEntity> transactionsByBuyerId;
}
