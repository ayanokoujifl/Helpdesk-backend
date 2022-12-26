package com.ayanokoujifl.helpdesk.domain.enums;

public enum Perfil {

	ADMIN(0, "Administrador"), CLIENTE(1, "Cliente"), TECNICO(2, "Tecnico");

	private Integer codigo;
	private String descricao;


	public static Perfil  toEnum(Integer cod) {
		if(cod==null) {
			return null;
		}
		
		for(Perfil x : Perfil.values()) {
			if(cod.equals(x.getCodigo())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Perfil invalido");
	}
	
	private Perfil() {
	}

	private Perfil(Integer codigo, String descricao) {
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