package com.ayanokoujifl.helpdesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayanokoujifl.helpdesk.domain.Cliente;
import com.ayanokoujifl.helpdesk.domain.Pessoa;
import com.ayanokoujifl.helpdesk.dto.ClienteDTO;
import com.ayanokoujifl.helpdesk.repositories.ClienteRepository;
import com.ayanokoujifl.helpdesk.repositories.PessoaRepository;
import com.ayanokoujifl.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.ayanokoujifl.helpdesk.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	@Autowired
	PessoaRepository pessoaRepository;


	@Autowired
	BCryptPasswordEncoder encoder;
	
	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! ID: " + id));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente save(ClienteDTO objDto) {
		objDto.setId(null);
		objDto.setSenha(encoder.encode(objDto.getSenha()));
		validaPorCpfEmail(objDto);
		Cliente obj = new Cliente(objDto);
		return repository.save(obj);
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDto) {
		objDto.setId(id);
		Cliente obj = findById(id);
		validaPorCpfEmail(objDto);
		obj = new Cliente(objDto);
		return repository.saveAndFlush(obj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if (obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					obj.getNome() + " não pode ser deletado pois possui chamados atrelados a ele!");
		}
			repository.deleteById(id);
	}

	private void validaPorCpfEmail(ClienteDTO objDto) {
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
