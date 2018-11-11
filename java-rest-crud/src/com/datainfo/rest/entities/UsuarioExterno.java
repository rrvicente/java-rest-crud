package com.datainfo.rest.entities;

public class UsuarioExterno {
	private String nu_cpf;
	private String no_usuario;
	private String de_email;
	private char ic_situacao;
	private int ic_perfil_acesso;
	private int co_funcao;
	private String nu_telefone;

	public String getNu_cpf() {
		return nu_cpf;
	}

	public void setNu_cpf(String nu_cpf) {
		this.nu_cpf = nu_cpf;
	}

	public String getNo_usuario() {
		return no_usuario;
	}

	public void setNo_usuario(String no_usuario) {
		this.no_usuario = no_usuario;
	}

	public String getDe_email() {
		return de_email;
	}

	public void setDe_email(String de_email) {
		this.de_email = de_email;
	}

	public char getIc_situacao() {
		return ic_situacao;
	}

	public void setIc_situacao(char ic_situacao) {
		this.ic_situacao = ic_situacao;
	}

	public int getIc_perfil_acesso() {
		return ic_perfil_acesso;
	}

	public void setIc_perfil_acesso(int ic_perfil_acesso) {
		this.ic_perfil_acesso = ic_perfil_acesso;
	}

	public int getCo_funcao() {
		return co_funcao;
	}

	public void setCo_funcao(int co_funcao) {
		this.co_funcao = co_funcao;
	}

	public String getNu_telefone() {
		return nu_telefone;
	}

	public void setNu_telefone(String nu_telefone) {
		this.nu_telefone = nu_telefone;
	}

}
