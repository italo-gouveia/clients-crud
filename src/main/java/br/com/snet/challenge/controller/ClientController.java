package br.com.snet.challenge.controller;

import br.com.snet.challenge.data.vo.ClientVO;
import br.com.snet.challenge.services.ClientServices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Api(tags = "ClientEndpoint")
@RestController
@RequestMapping("/api/v1/client")
public class ClientController {

	@Autowired
	private ClientServices service;

    @Autowired
    private PagedResourcesAssembler<ClientVO> assembler;

    private static final String DESC_CONST = "desc";

    @ApiOperation(value = "Busca todos os clientes" )
    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findAll(
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {

        Direction sortDirection = DESC_CONST.equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

        Page<ClientVO> clients =  service.findAll(pageable);
        clients
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(ClientController.class).findById(p.getKey())).withSelfRel()
                        )
                );

        PagedResources<?> resources = assembler.toResource(clients);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca clientes pelo nome" )
    @GetMapping(value = "/findPersonByName/{name}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<?> findPersonByName(
            @PathVariable("name") String name,
            @RequestParam(value="page", defaultValue = "0") int page,
            @RequestParam(value="limit", defaultValue = "12") int limit,
            @RequestParam(value="direction", defaultValue = "asc") String direction) {

        Direction sortDirection = DESC_CONST.equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;

        Pageable pageable = PageRequest.of(page, limit, Sort.by(sortDirection, "name"));

        Page<ClientVO> persons =  service.findClientByName(name, pageable);
        persons
                .stream()
                .forEach(p -> p.add(
                        linkTo(methodOn(ClientController.class).findById(p.getKey())).withSelfRel()
                        )
                );

        PagedResources<?> resources = assembler.toResource(persons);

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @ApiOperation(value = "Busca cliente por ID" )
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO findById(@PathVariable("id") Long id) {
		ClientVO clientVO = service.findById(id);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(id)).withSelfRel());
		return clientVO;
	}

	@ApiOperation(value = "Cria um novo cliente")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO create(@RequestBody ClientVO client) {
		ClientVO clientVO = service.create(client);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(clientVO.getKey())).withSelfRel());
		return clientVO;
	}

	@ApiOperation(value = "Atualiza um cliente pelo ID")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" },
			consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ClientVO update(@RequestBody ClientVO client) {
		ClientVO clientVO = service.update(client);
		clientVO.add(linkTo(methodOn(ClientController.class).findById(clientVO.getKey())).withSelfRel());
		return clientVO;
	}

	@ApiOperation(value = "Deleta um cliente pelo ID")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}

}