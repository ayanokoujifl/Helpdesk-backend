package com.ayanokoujifl.helpdesk.domain.enums;

public enum Status {

	ABERTO(0, "Aberto"), ANDAMENTO(1, "Andamento"), ENCERRADO(2, "Encerrado");

	private Integer codigo;
	private String descricao;


	public static Status  toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Status x : Status.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Status invalido");
	}
	
	private Status() {
	}

	private Status(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
