package br.com.seguranca;

import java.util.Base64;

public class Perfil {
	private String login;
	private String senha;
	private String senhaCriptada;
	private Tipo tipo;
	
	public Perfil(String login, String senha, Tipo tipo) {
		this.login = login;
		this.senhaCriptada = CriptarSenha(senha);
		this.tipo = tipo;
	}

	private String CriptarSenha(String senha) {
		return Base64.getEncoder().encodeToString(senha.getBytes());
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public String getSenhaCriptada() {
		return senhaCriptada;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	
}
