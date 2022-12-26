package com.ayanokoujifl.helpdesk.services;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayanokoujifl.helpdesk.domain.Chamado;
import com.ayanokoujifl.helpdesk.domain.Cliente;
import com.ayanokoujifl.helpdesk.domain.Tecnico;
import com.ayanokoujifl.helpdesk.domain.enums.Prioridade;
import com.ayanokoujifl.helpdesk.domain.enums.Status;
import com.ayanokoujifl.helpdesk.dto.ChamadoDTO;
import com.ayanokoujifl.helpdesk.repositories.ChamadoRepository;
import com.ayanokoujifl.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ChamadoService {

	@Autowired
	ChamadoRepository repository;

	@Autowired
	TecnicoService tecnicoService;

	@Autowired
	ClienteService clienteService;

	public Chamado findById(Integer id) {
		return repository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado save(ChamadoDTO objDTO) {
		return repository.save(newChamado(objDTO));
	}

	public Chamado update(Integer id, @Valid ChamadoDTO objDto) {
		objDto.setId(id);
		Chamado obj = findById(id);
		obj = newChamado(objDto);
		return repository.saveAndFlush(obj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		Chamado chamado = new Chamado();
		if (obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}
}
