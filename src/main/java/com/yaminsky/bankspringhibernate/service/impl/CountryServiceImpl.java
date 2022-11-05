package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.dto.assembler.CountryDtoAssembler;
import com.yaminsky.bankspringhibernate.exception.CountryNotFoundException;
import com.yaminsky.bankspringhibernate.repository.ICountryRepository;
import com.yaminsky.bankspringhibernate.service.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements ICountryService {
    private ICountryRepository countryRepository;
    private CountryDtoAssembler countryDtoAssembler;

    @Override
    public CollectionModel<CountryDto> findAll() {
        List<CountryEntity> countries = countryRepository.findAll();
        return countryDtoAssembler.toCollectionModel(countries);
    }

    @Override
    public CountryDto findById(Integer id) {
        CountryEntity country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException("Country with id " + id + " not found"));
        return countryDtoAssembler.toModel(country);
    }

    @Override
    @Transactional
    public CountryDto create(CountryDto country) {
        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setName(country.getName());
        countryRepository.save(countryEntity);
        return country;
    }

    @Override
    @Transactional
    public void update(Integer id, CountryDto newCountry) {
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