package com.ayanokoujifl.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ayanokoujifl.helpdesk.domain.Chamado;
import com.ayanokoujifl.helpdesk.dto.ChamadoDTO;
import com.ayanokoujifl.helpdesk.services.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	ChamadoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
		Chamado obj = service.findById(id);
		ChamadoDTO objDto = new ChamadoDTO(obj);
		return ResponseEntity.ok().body(objDto);
	}

	@GetMapping
	public ResponseEntity<List<ChamadoDTO>> findAll() {
		List<Chamado> chamados = service.findAll();
		List<ChamadoDTO> dtos = chamados.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(dtos);
	}

	@PostMapping
	public ResponseEntity<ChamadoDTO> save(@Valid @RequestBody ChamadoDTO objDTO) {
		Chamado obj = service.save(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDto){
		Chamado obj = service.update(id,objDto);
		return ResponseEntity.ok().body(new ChamadoDTO(obj));
	}
}