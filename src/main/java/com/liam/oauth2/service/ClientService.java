package com.liam.oauth2.service;

import com.liam.oauth2.entity.Client;
import com.liam.oauth2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author: liangzy
 * @date: 2019/03/15 下午2:52
 * @desc:
 */

@Service
public class ClientService extends BaseService{

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientRepository getRepository() {
        return this.clientRepository;
    }

    public boolean save(){
        Client client = new Client();
        client.setAccess_token_validity(60*60*2);
        client.setClient_id(UUID.randomUUID().toString());
        client.setClient_secret("secrect123");
        return super.save(client);
    }

}
