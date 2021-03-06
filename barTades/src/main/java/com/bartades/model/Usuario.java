package com.bartades.model;

import java.util.ArrayList;

public class Usuario {

	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String senha;
	private String telefone;
	private String sexo;
	private String unidadeAtuacao;
	private String cargo;
	private int nivelAcesso;

	public Usuario() {
	}

	public Usuario(String nome, String cpf, String email, String senha, String telefone, String sexo,
			String unidadeAtuacao, String cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.sexo = sexo;
		this.unidadeAtuacao = unidadeAtuacao;
		this.cargo = cargo;
	}

	public Usuario(int id, String nome, String cpf, String email, String senha, String telefone, String sexo,
			String unidadeAtuacao, String cargo) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.sexo = sexo;
		this.unidadeAtuacao = unidadeAtuacao;
		this.cargo = cargo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return cpf;
	}

	public void setCPF(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getUnidadeAtuacao() {
		return unidadeAtuacao;
	}

	public int getId() {
		return this.id;
	}

	public void setUnidadeAtuacao(String unidadeAtuacao) {
		this.unidadeAtuacao = unidadeAtuacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
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
		Usuario other = (Usuario) obj;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", email=" + email + ", senha=" + senha
				+ ", telefone=" + telefone + ", sexo=" + sexo + ", unidadeAtuacao=" + unidadeAtuacao + ", cargo="
				+ cargo + "]";
	}

	public int getNivelAcesso() {
		return nivelAcesso;
	}

	public void setNivelAcesso(int nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	public void encontrarNivelAcesso(ArrayList<Cargo> listaCargos) {
		for(Cargo c : listaCargos) {
			if(c.getNome().equals(this.getCargo())) {
				this.setNivelAcesso(c.getNivelAcesso());
			}
		}
	}

}
