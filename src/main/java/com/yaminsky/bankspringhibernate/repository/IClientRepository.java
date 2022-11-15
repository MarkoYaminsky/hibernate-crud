package com.yaminsky.bankspringhibernate.repository;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Integer> {
    List<ClientEntity> getClientEntitiesByCountryByCountryId(CountryEntity countryByCountryId);

    @Procedure("insert_ten_records_into_client")
    void insertTenRecordsIntoClient();
}
