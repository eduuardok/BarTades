package com.bartades.model;

public class Cargo {
	
	private int _ID;
	private String nome;
	private int nivelAcesso;
	
	public Cargo(int _ID, String nome, int nivelAcesso) {
		this._ID = _ID;
		this.nome = nome;
		this.nivelAcesso = nivelAcesso;
	}

	public int getId() {
		return _ID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	

}
