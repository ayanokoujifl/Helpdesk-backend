package com.ayanokoujifl.helpdesk.domain.enums;

public enum Prioridade {

	BAIXA(0, "Baixa"), MEDIA(1, "Media"), ALTA(2, "Alta");

	private Integer codigo;
	private String descricao;


	public static Prioridade  toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Prioridade x : Prioridade.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Prioridade invalido");
	}
	
	private Prioridade() {
	}

	private Prioridade(Integer codigo, String descricao) {
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
