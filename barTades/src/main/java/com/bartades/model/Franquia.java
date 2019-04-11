package com.bartades.model;

public class Franquia {
	private int ID;
	private String nome;
	private String estado;
	private String endereco;
	
	public Franquia () {
	}
	
	public Franquia(String nome, String estado, String endereco) {
		this.nome = nome;
		this.estado = estado;
		this.endereco = endereco;
	}
	
	public Franquia(int ID, String nome, String estado, String endereco) {
		this.ID = ID;
		this.nome = nome;
		this.estado = estado;
		this.endereco = endereco;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	public int getId() {
		return this.ID;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Franquia other = (Franquia) obj;
		if (ID != other.ID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Franquia [ID=" + ID + ", nome=" + nome + ", estado=" + estado + ", endereco=" + endereco + "]";
	}
	
}
