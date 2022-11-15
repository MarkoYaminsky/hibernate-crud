package com.yaminsky.bankspringhibernate.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "continent", schema = "marko_yaminsky", catalog = "")
public class ContinentEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
}
