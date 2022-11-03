package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "bank", schema = "marko_yaminsky", catalog = "")
public class BankEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id", nullable = false)
    private CountryEntity countryByCountryId;
    @OneToMany(mappedBy = "bankByBankId")
    private List<BankAccountEntity> bankAccountsById;
}
