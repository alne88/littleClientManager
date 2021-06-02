package com.example.demo.services;

import com.example.demo.models.ClientDto;
import com.example.demo.persistence.ClientsDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientsDao clientsDao;
    private final UserService userService;

    public ClientService(ClientsDao clientsDao, UserService userService) {
        this.clientsDao = clientsDao;
        this.userService = userService;
    }

    public void insert(ClientDto clientDto) {
        clientsDao.insert(clientDto);
    }

    public void update(ClientDto clientDto) {
        clientsDao.update(clientDto);
    }

    public void delete(Long clientId) {
        clientsDao.delete(clientId);
    }

    public List<ClientDto> findUserClients() {
        Long userId = userService.getLoggedInUserId();
        return clientsDao.findUserClients(userId);
    }

    public ClientDto findById(Long id) {
        return clientsDao.findById(id);
    }
}
