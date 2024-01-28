package com.raffler.mscreditrating.infra.client;
import com.raffler.mscreditrating.domain.model.ClientData;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(value = "msclient", path = "/client")
public interface ClientResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<ClientData> clientData(@RequestParam("cpf") String cpf);



}
