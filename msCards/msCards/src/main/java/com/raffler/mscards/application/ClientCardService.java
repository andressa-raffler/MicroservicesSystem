package com.raffler.mscards.application;

import com.raffler.mscards.domain.ClientCard;
import com.raffler.mscards.infra.repository.ClientCardRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    @Autowired
    private ClientCardRespository clientCardRespositoryrespository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return clientCardRespositoryrespository.findByCpf(cpf);
    }

}
