package com.raffler.msclient.application;

import com.raffler.msclient.domain.Client;
import com.raffler.msclient.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository repository;

    //client repository

    @Transactional
    public Client save(Client client){
        return repository.save(client);
    }


    public Optional<Client> getByCPF(String cpf){
        return repository.findByCpf( cpf);


    }
}
