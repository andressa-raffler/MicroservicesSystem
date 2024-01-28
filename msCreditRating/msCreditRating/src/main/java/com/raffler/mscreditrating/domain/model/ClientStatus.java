package com.raffler.mscreditrating.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientStatus {
    private ClientData clientData;
    private List<ClientCards> clientCardsList;

}
