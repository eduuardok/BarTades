/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.model;

/**
 *
 * @author ELuna
 */
public class Produto {
    
    private int _ID;
    private String nome;
    private String descricao;
    private String categoria;
    private double precoVenda;
    private double precoCompra;
    private String fornecedor;
    
    public Produto(String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor){
        
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.fornecedor = fornecedor;
        
    }
    
    public Produto(int id, String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor){
        
        this._ID = id;
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.fornecedor = fornecedor;
    }
    
    public int getId(){
        return this._ID;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public String getDescricao(){
        return this.descricao;
    }
    
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    
    public String getCategoria(){
        return this.categoria;
    }
    
    public void setPrecoVenda(double precoVenda){
        this.precoVenda = precoVenda;
    }
    
    public double getPrecoVenda(){
        return this.precoVenda;
    }
    
    public void setPrecoCompra(double precoCompra){
        this.precoCompra = precoCompra;
    }
    
    public double getPrecoCompra(){
        return this.precoCompra;
    }
    
    public void setFornecedor(String fornecedor){
        this.fornecedor = fornecedor;
    }
    
    public String getFornecedor(){
        return this.fornecedor;
    }
    
}
