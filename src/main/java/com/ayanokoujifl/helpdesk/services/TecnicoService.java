package com.ayanokoujifl.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayanokoujifl.helpdesk.domain.Pessoa;
import com.ayanokoujifl.helpdesk.domain.Tecnico;
import com.ayanokoujifl.helpdesk.dto.TecnicoDTO;
import com.ayanokoujifl.helpdesk.repositories.PessoaRepository;
import com.ayanokoujifl.helpdesk.repositories.TecnicoRepository;
import com.ayanokoujifl.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.ayanokoujifl.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	TecnicoRepository repository;

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Tecnico não encontrado! ID: " + id));
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico save(TecnicoDTO objDto) {
		objDto.setId(null);
		objDto.setSenha(encoder.encode(objDto.getSenha()));
		validaPorCpfEmail(objDto);
		Tecnico obj = new Tecnico(objDto);
		return repository.save(obj);
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDto) {
		objDto.setId(id);
		Tecnico obj = findById(id);
		validaPorCpfEmail(objDto);
		obj = new Tecnico(objDto);
		return repository.saveAndFlush(obj);
	}

	public void delete(Integer id) {
		Tecnico obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					obj.getNome() + " não pode ser deletado pois possui chamados atrelados a ele!");
		}
			repository.deleteById(id);
	}

	private void validaPorCpfEmail(TecnicoDTO objDto) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
		}
		obj = pessoaRepository.findByEmail(objDto.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
		}
	}
}
