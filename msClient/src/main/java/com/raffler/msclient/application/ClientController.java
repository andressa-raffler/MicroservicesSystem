package com.raffler.msclient.application;

import com.raffler.msclient.application.representation.ClientSaveRequest;
import com.raffler.msclient.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
@Slf4j
public class ClientController {

    private final ClientService service;

    @GetMapping
    public String status(){
        log.info("Getting state from client microservice");
        return "ok";
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ClientSaveRequest request){
        Client client = request.toModel();
        service.save(client);
        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam("cpf") String cpf){
        Optional<Client> client = service.getByCPF(cpf);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

}
