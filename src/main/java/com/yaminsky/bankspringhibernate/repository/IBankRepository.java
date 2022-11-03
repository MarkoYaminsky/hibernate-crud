package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.BankEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBankRepository extends JpaRepository<BankEntity, Integer> {
    List<BankEntity> getBankEntitiesByCountryByCountryId(CountryEntity countryByCountryId);
}
