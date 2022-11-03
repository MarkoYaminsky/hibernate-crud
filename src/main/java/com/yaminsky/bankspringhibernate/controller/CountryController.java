package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.dto.assembler.CountryDtoAssembler;
import com.yaminsky.bankspringhibernate.service.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {
    private ICountryService countryService;
    private CountryDtoAssembler clientDtoAssembler;

    @GetMapping(value = "/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer countryId) {
        CountryEntity country = countryService.findById(countryId);
        CountryDto countryDto = clientDtoAssembler.toModel(country);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllCountries() {
        List<CountryEntity> countries = countryService.findAll();
        CollectionModel<CountryDto> countryDto = clientDtoAssembler.toCollectionModel(countries);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryEntity country) {
        CountryEntity newCountry = countryService.create(country);
        CountryDto countryDto = clientDtoAssembler.toModel(newCountry);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody CountryEntity newCountry, @PathVariable Integer countryId) {
        countryService.update(countryId, newCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
