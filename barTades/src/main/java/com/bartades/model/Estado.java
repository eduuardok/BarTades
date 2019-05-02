
package com.bartades.model;

/**
 *
 * @author Antonio Carlos
 */
public class Estado {
    
    
	private int ID;
	private String nome;
	
	public Estado(int ID, String nome) {
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
