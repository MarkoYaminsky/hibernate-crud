package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "country", schema = "marko_yaminsky", catalog = "")
public class CountryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "countryByCountryId")
    private List<BankEntity> banksById;
    @OneToMany(mappedBy = "countryByCountryId")
    private List<ClientEntity> clientsById;
}
