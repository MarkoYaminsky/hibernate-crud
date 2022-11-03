package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICountryRepository extends JpaRepository<CountryEntity, Integer> {
}
