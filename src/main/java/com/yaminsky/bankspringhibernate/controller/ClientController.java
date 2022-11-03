package com.yaminsky.bankspringhibernate.controller;

import com.yaminsky.bankspringhibernate.domain.ClientEntity;
import com.yaminsky.bankspringhibernate.dto.ClientDto;
import com.yaminsky.bankspringhibernate.dto.assembler.ClientDtoAssembler;
import com.yaminsky.bankspringhibernate.service.IClientService;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/clients")
public class ClientController {
    private IClientService clientService;
    private ClientDtoAssembler clientDtoAssembler;

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
        ClientEntity client = clientService.findById(clientId);
        ClientDto clientDto = clientDtoAssembler.toModel(client);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<CollectionModel<ClientDto>> getAllClients() {
        List<ClientEntity> clients = clientService.findAll();
        CollectionModel<ClientDto> clientDto = clientDtoAssembler.toCollectionModel(clients);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientEntity client) {
        ClientEntity newClient = clientService.create(client);
        ClientDto clientDto = clientDtoAssembler.toModel(newClient);
        return new ResponseEntity<>(clientDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@RequestBody ClientEntity newClient, @PathVariable Integer clientId) {
        clientService.update(clientId, newClient);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
