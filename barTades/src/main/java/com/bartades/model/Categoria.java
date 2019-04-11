package com.bartades.model;

public class Categoria {

	private int ID;
	private String nome;
	
	public Categoria(int ID, String nome) {
		this.ID = ID;
		this.nome = nome;
	}
	
	public int getId() {
		return this.ID;
	}
	
	public String getNome(){
		return this.nome;
	}
	
}
