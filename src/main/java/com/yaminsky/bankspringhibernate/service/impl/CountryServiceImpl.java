package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.exception.CountryNotFoundException;
import com.yaminsky.bankspringhibernate.repository.ICountryRepository;
import com.yaminsky.bankspringhibernate.service.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements ICountryService {
    private ICountryRepository countryRepository;

    @Override
    public List<CountryEntity> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public CountryEntity findById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country with id " + id + " not found"));
    }

    @Override
    @Transactional
    public CountryEntity create(CountryEntity country) {
        countryRepository.save(country);
        return country;
    }

    @Override
    @Transactional
    public void update(Integer id, CountryEntity newCountry) {
        CountryEntity country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country with id" + id + " not found"));
        country.setName(newCountry.getName());
        countryRepository.save(country);
    }

    @Override
    public void delete(Integer id) {
        CountryEntity CountryEntity = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country with id" + id + " not found"));
        countryRepository.delete(CountryEntity);
    }
}