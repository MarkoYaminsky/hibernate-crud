package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.controller.ContinentController;
import com.yaminsky.bankspringhibernate.domain.ContinentEntity;
import com.yaminsky.bankspringhibernate.dto.ContinentDto;
import com.yaminsky.bankspringhibernate.dto.assembler.ContinentDtoAssembler;
import com.yaminsky.bankspringhibernate.exception.ContinentNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IContinentRepository;
import com.yaminsky.bankspringhibernate.service.IContinentService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@AllArgsConstructor
public class ContinentServiceImpl implements IContinentService {
    private IContinentRepository continentRepository;
    private ContinentDtoAssembler continentDtoAssembler;

    @Override
    public CollectionModel<ContinentDto> findAll() {
        List<ContinentEntity> continents = continentRepository.findAll();
        return continentDtoAssembler.toCollectionModel(continents);
    }

    @Override
    public ContinentDto findById(Integer id) {
        ContinentEntity country = continentRepository.findById(id)
                .orElseThrow(() -> new ContinentNotFoundException("Continent with id " + id + " not found"));
        return continentDtoAssembler.toModel(country);
    }

    @Override
    @Transactional
    public ContinentDto create(ContinentDto continent) {
        ContinentEntity countryEntity = new ContinentEntity();
        countryEntity.setName(continent.getName());
        continentRepository.save(countryEntity);
        return continent;
    }

    @Override
    @Transactional
    public void update(Integer id, ContinentDto newContinent) {
        ContinentEntity continent = continentRepository.findById(id)
                .orElseThrow(() -> new ContinentNotFoundException("Continent with id" + id + " not found"));
        continent.setName(newContinent.getName());
        continentRepository.save(continent);
    }

    @Override
    public void delete(Integer id) {
        ContinentEntity CountryEntity = continentRepository.findById(id)
                .orElseThrow(() -> new ContinentNotFoundException("Continent with id" + id + " not found"));
        continentRepository.delete(CountryEntity);
    }

    @Override
    public ContinentDto parametrizedInsertIntoTable(ContinentDto continentDto) {
        Integer id = continentRepository.parametrizedInsertIntoTable(continentDto.getName());
        Link selfLink = linkTo(methodOn(ContinentController.class).getContinent(id)).withSelfRel();
        continentDto.add(selfLink);
        return continentDto;
    }
}
