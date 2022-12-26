package com.ayanokoujifl.helpdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ayanokoujifl.helpdesk.domain.Chamado;
import com.ayanokoujifl.helpdesk.domain.Cliente;
import com.ayanokoujifl.helpdesk.domain.Tecnico;
import com.ayanokoujifl.helpdesk.domain.enums.Perfil;
import com.ayanokoujifl.helpdesk.domain.enums.Prioridade;
import com.ayanokoujifl.helpdesk.domain.enums.Status;
import com.ayanokoujifl.helpdesk.repositories.ChamadoRepository;
import com.ayanokoujifl.helpdesk.repositories.ClienteRepository;
import com.ayanokoujifl.helpdesk.repositories.TecnicoRepository;

@Service
public class DBService {

	@Autowired
	TecnicoRepository tecnicoRepository;

	@Autowired
	ClienteRepository clienteRepository;
 
	@Autowired 
	ChamadoRepository chamadoRepository;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	public void instantiateDatabase() {

		Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "60946684480", "valdir@email.com", encoder.encode("123"));
		tec1.addPerfis(Perfil.ADMIN);
		Tecnico tec2 = new Tecnico(null, "Luis Gustavo", "16564423616", "guleite3@gmail.com", encoder.encode("141730"));
		tec2.addPerfis(Perfil.ADMIN);

		Cliente cli1 = new Cliente(null, "Linus Torvalds", "27196711262", "linus@gmail.com", encoder.encode("123"));

		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado01", "Primeiro chamado", tec1, cli1);

		tecnicoRepository.saveAll(Arrays.asList(tec1));
		clienteRepository.saveAll(Arrays.asList(cli1));
		chamadoRepository.saveAll(Arrays.asList(c1));
	}
	
}
