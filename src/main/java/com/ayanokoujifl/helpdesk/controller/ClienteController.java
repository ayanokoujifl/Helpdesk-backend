package com.ayanokoujifl.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ayanokoujifl.helpdesk.domain.Cliente;
import com.ayanokoujifl.helpdesk.dto.ClienteDTO;
import com.ayanokoujifl.helpdesk.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
		Cliente obj = service.findById(id);
		return ResponseEntity.ok().body(new ClienteDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> Clientes = service.findAll();
		List<ClienteDTO> ClientesDto = Clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ClientesDto);
	}

	@PostMapping
	public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO objDto) {
		Cliente obj = service.save(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente oldObj = service.update(id, objDto);
		return ResponseEntity.ok().body(new ClienteDTO(oldObj));
	}
 
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
