package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.ContinentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;

public interface IContinentRepository extends JpaRepository<ContinentEntity, Integer> {
    @Procedure("insertion_into_continent")
    Integer parametrizedInsertIntoTable(String name);
}
