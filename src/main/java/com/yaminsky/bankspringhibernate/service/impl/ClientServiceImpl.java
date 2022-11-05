package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.dto.ClientDto;
import com.yaminsky.bankspringhibernate.dto.CountryDto;
import com.yaminsky.bankspringhibernate.dto.assembler.ClientDtoAssembler;
import com.yaminsky.bankspringhibernate.exception.ClientNotFoundException;
import com.yaminsky.bankspringhibernate.exception.CountryNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IClientRepository;
import com.yaminsky.bankspringhibernate.repository.ICountryRepository;
import com.yaminsky.bankspringhibernate.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private IClientRepository clientRepository;
    private ClientDtoAssembler clientDtoAssembler;
    private ICountryRepository countryRepository;

    @Override
    public CollectionModel<ClientDto> findAll() {
        List<ClientEntity> clients = clientRepository.findAll();
        return clientDtoAssembler.toCollectionModel(clients);
    }

    @Override
    public ClientDto findById(Integer id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found"));
        return clientDtoAssembler.toModel(client);
    }

    @Override
    @Transactional
    public ClientDto create(ClientDto client) {
        ClientEntity clientEntity = new ClientEntity();
        CountryEntity country = countryRepository.findById(client.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        clientEntity.setCountryByCountryId(country);
        clientEntity.setFirstName(client.getFirstName());
        clientEntity.setLastName(client.getLastName());
        clientRepository.save(clientEntity);
        return client;
    }

    @Override
    @Transactional
    public void update(Integer id, ClientDto newClient) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id" + id + " not found"));
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        client.setCountryByCountryId(countryRepository.findById(newClient.getCountryId()).orElseThrow(() -> new CountryNotFoundException("Country not found")));
        clientRepository.save(client);
    }

    @Override
    public void delete(Integer id) {
        ClientEntity ClientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id" + id + " not found"));
        clientRepository.delete(ClientEntity);
    }

    @Override
    public CollectionModel<ClientDto> getClientEntitiesByCountryByCountryId(Integer id) {
        CountryEntity country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFoundException("Country not found"));
        List<ClientEntity> clientEntities = clientRepository.getClientEntitiesByCountryByCountryId(country);
        return clientDtoAssembler.toCollectionModel(clientEntities);
    }
}