package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.service.ICountryService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/countries")
public class CountryController {
    private ICountryService countryService;

    @GetMapping(value = "/{countryId}")
    public ResponseEntity<CountryDto> getCountry(@PathVariable Integer countryId) {
        CountryDto countryDto = countryService.findById(countryId);
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<CountryDto>> getAllCountries() {
        CollectionModel<CountryDto> countryDto = countryService.findAll();
        return new ResponseEntity<>(countryDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CountryDto> addCountry(@RequestBody CountryDto country) {
        CountryDto countryDto = countryService.create(country);
        return new ResponseEntity<>(countryDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{countryId}")
    public ResponseEntity<?> updateCountry(@RequestBody CountryDto newCountry, @PathVariable Integer countryId) {
        countryService.update(countryId, newCountry);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{countryId}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer countryId) {
        countryService.delete(countryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
