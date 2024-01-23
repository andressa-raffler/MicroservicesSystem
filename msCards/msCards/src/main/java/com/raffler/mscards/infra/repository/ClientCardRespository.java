package com.raffler.mscards.infra.repository;


import com.raffler.mscards.domain.ClientCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientCardRespository extends JpaRepository<ClientCard, Long> {
    List<ClientCard>findByCpf(String cpf);


}
