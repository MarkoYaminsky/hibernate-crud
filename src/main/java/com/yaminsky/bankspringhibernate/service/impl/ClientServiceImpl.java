package com.yaminsky.bankspringhibernate.service.impl;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.domain.CountryEntity;
import com.yaminsky.bankspringhibernate.exception.ClientNotFoundException;
import com.yaminsky.bankspringhibernate.repository.IClientRepository;
import com.yaminsky.bankspringhibernate.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements IClientService {
    private IClientRepository clientRepository;

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id " + id + " not found"));
    }

    @Override
    @Transactional
    public ClientEntity create(ClientEntity client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    @Transactional
    public void update(Integer id, ClientEntity newClient) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id" + id + " not found"));
        client.setFirstName(newClient.getFirstName());
        client.setLastName(newClient.getLastName());
        clientRepository.save(client);
    }

    @Override
    public void delete(Integer id) {
        ClientEntity ClientEntity = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client with id" + id + " not found"));
        clientRepository.delete(ClientEntity);
    }

    @Override
    public List<ClientEntity> getClientEntitiesByCountryByCountryId(CountryEntity countryByCountryId) {
        return clientRepository.getClientEntitiesByCountryByCountryId(countryByCountryId);
    }
}