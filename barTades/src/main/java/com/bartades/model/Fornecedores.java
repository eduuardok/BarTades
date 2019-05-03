package com.bartades.model;

/**
 *
 * @author Antonio Carlos
 */
public class Fornecedores {

    private int _ID;
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean disponibilidade;

    
    public Fornecedores(String nome, String cnpj, String telefone, String endereco, String numero, String complemento, String cep,
            String bairro, String cidade, String estado, boolean disponibilidade) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.disponibilidade = disponibilidade;
    }

    public Fornecedores(int id, String nome, String cnpj, String telefone, String endereco, String numero, String complemento, String cep,
            String bairro, String cidade, String estado, boolean disponibilidade) {
       
        this._ID = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.disponibilidade = disponibilidade;
    }

    public Fornecedores() {
    }
    /**
     * @return the ID
     */
    public int getID() {
        return this._ID;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public boolean getDisponibilidade() {
        return this.disponibilidade;
    }

    @Override
    public String toString() {
        return "Fornecedor " + nome + ", CNPJ " + cnpj + ", Contato " + telefone + ", Rua " + endereco + " Numero: " + numero
                + " CEP " + cep;
    }

}
