package br.com.snet.challenge.services;

import br.com.snet.challenge.converter.DozerConverter;
import br.com.snet.challenge.data.model.Client;
import br.com.snet.challenge.data.vo.ClientVO;
import br.com.snet.challenge.exception.ResourceNotFoundException;
import br.com.snet.challenge.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientServices {

    @Autowired
    ClientRepository repository;

    public ClientVO create(ClientVO client) {
        Client entity = DozerConverter.parseObject(client, Client.class);
        ClientVO vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class);
        return vo;
    }

    public Page<ClientVO> findClientByName(String name, Pageable pageable) {
        Page<Client> page = repository.findClientByName(name, pageable);
        return page.map(this::convertToClientVO);
    }

    public Page<ClientVO> findAll(Pageable pageable) {
        Page<Client> page = repository.findAll(pageable);
        return page.map(this::convertToClientVO);
    }

    private ClientVO convertToClientVO(Client entity) {
        return DozerConverter.parseObject(entity, ClientVO.class);
    }

    public ClientVO findById(Long id) {

        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        return DozerConverter.parseObject(entity, ClientVO.class);
    }

    public ClientVO update(ClientVO client) {
        Client entity = repository.findById(client.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setName(client.getName());
        entity.setPhone(client.getPhone());
        entity.setAddress(client.getAddress());
        entity.setHouseNumber(client.getHouseNumber());
        entity.setCity(client.getCity());
        entity.setState(client.getState());
        entity.setCountry(client.getCountry());
        entity.setPostalCode(client.getPostalCode());

        ClientVO vo = DozerConverter.parseObject(repository.save(entity), ClientVO.class);
        return vo;
    }

    public void delete(Long id) {
        Client entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}