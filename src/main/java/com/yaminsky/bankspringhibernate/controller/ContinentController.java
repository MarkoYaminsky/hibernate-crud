package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.dto.ContinentDto;
import com.yaminsky.bankspringhibernate.service.IContinentService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/continents")
public class ContinentController {
    private IContinentService continentService;

    @GetMapping(value = "/{continentId}")
    public ResponseEntity<ContinentDto> getContinent(@PathVariable Integer continentId) {
        ContinentDto continentDto = continentService.findById(continentId);
        return new ResponseEntity<>(continentDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ContinentDto>> getAllContinents() {
        CollectionModel<ContinentDto> continentDto = continentService.findAll();
        return new ResponseEntity<>(continentDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ContinentDto> addContinent(@RequestBody ContinentDto continent) {
        ContinentDto continentDto = continentService.create(continent);
        return new ResponseEntity<>(continentDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{continentId}")
    public ResponseEntity<?> updateContinent(@RequestBody ContinentDto newContinent, @PathVariable Integer continentId) {
        continentService.update(continentId, newContinent);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{continentId}")
    public ResponseEntity<?> deleteContinent(@PathVariable Integer continentId) {
        continentService.delete(continentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/parametrized")
    public ResponseEntity<ContinentDto> addParametrizedContinent(@RequestBody ContinentDto continent) {
        ContinentDto continentDto = continentService.parametrizedInsertIntoTable(continent);
        return new ResponseEntity<>(continentDto, HttpStatus.CREATED);
    }
}

