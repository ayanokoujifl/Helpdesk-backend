package com.ayanokoujifl.helpdesk.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.ayanokoujifl.helpdesk.domain.Chamado;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ChamadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	@NotNull(message = "O campo PRIORIDADE é obrigatório!")
	private Integer prioridade;
	@NotNull(message = "O campo STATUS é obrigatório!")
	private Integer status;
	@NotNull(message = "O campo TITULO é obrigatório!")
	private String titulo;
	@NotNull(message = "O campo OBSERVAÇÕES é obrigatório!")
	private String observacoes;
	@NotNull(message = "O campo TECNICO é obrigatório!")
	private Integer tecnico;
	@NotNull(message = "O campo CLIENTE é obrigatório!")
	private Integer cliente;
	private String nomeTecnico;
	private String nomeCliente;

	public ChamadoDTO() {
	}

	public ChamadoDTO(Chamado obj) {
		id = obj.getId();
		dataAbertura = obj.getDataAbertura();
		dataFechamento = obj.getDataFechamento();
		prioridade = obj.getPrioridade().getCodigo();
		status = obj.getStatus().getCodigo();
		titulo = obj.getTitulo();
		observacoes = obj.getObservacoes();
		tecnico = obj.getTecnico().getId();
		cliente = obj.getCliente().getId();
		nomeTecnico = obj.getTecnico().getNome();
		nomeCliente = obj.getCliente().getNome();
	}

	public ChamadoDTO(Integer id, LocalDate dataAbertura, LocalDate dataFechamento, Integer prioridade, Integer status,
			String titulo, String observacoes, Integer tecnico, Integer cliente, String nomeTecnico,
			String nomeCliente) {
		super();
		this.id = id;
		this.dataAbertura = dataAbertura;
		this.dataFechamento = dataFechamento;
		this.prioridade = prioridade;
		this.status = status;
		this.titulo = titulo;
		this.observacoes = observacoes;
		this.tecnico = tecnico;
		this.cliente = cliente;
		this.nomeTecnico = nomeTecnico;
		this.nomeCliente = nomeCliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
}