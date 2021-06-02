package com.example.demo.persistence;

import com.example.demo.models.ClientDto;

import java.util.List;

public interface ClientsDao {

    void insert(ClientDto client);

    void update(ClientDto clientDto);

    void delete(Long clientId);

    List<ClientDto> findUserClients(Long userId);

    ClientDto findById(Long id);
}
