/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.model;
import com.bartades.model.Produto;
import java.util.LinkedList;

/**
 *
 * @author vitor.lsantos5
 */
public class Pedido {

    private int ID;
    private LinkedList<Produto>produtos;
    private String tipoPagamento;
    private String unidade;
    private String nomeCliente;

    public Pedido(int ID, String tipoPagamento, String unidade, String nomeCliente) {

        this.ID = ID;
        this.tipoPagamento = tipoPagamento;
        this.unidade = unidade;
        this.nomeCliente = nomeCliente;
        produtos = new LinkedList();

    }

    public Pedido(String tipoPagamento, String unidade, String nomeCliente) {

        this.tipoPagamento = tipoPagamento;
        this.unidade = unidade;
        this.nomeCliente = nomeCliente;
        produtos = new LinkedList();
        
    }

    public void removerProduto(Produto p) {
        
        for(Produto produto: produtos) {
            
            if(produto.equals(p)) {
                produtos.remove(produto.getId());
            }
            
        }
        
    }
    
    public void adicionarProduto(Produto p) {
        
        produtos.add(p);
        
    }
    
    public int getID() {
        return this.ID;
    }

    public String getTipoPagamento() {
        return this.tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public String getUnidade() {
        return this.unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
