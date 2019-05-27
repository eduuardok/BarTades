/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.model;
import com.bartades.model.Produto;
import java.util.LinkedList;
import com.bartades.model.Franquia;
/**
 *
 * @author vitor.lsantos5
 */
public class Pedido {

    private int ID;
    private LinkedList<Produto>produtos;
    private String tipoPagamento;
    private int unidade;
    private String nomeCliente;

    public Pedido(int ID, String tipoPagamento, int unidade, String nomeCliente) {

        this.ID = ID;
        this.tipoPagamento = tipoPagamento;
        this.unidade = unidade;
        this.nomeCliente = nomeCliente;
        produtos = new LinkedList();

    }

    public Pedido(String tipoPagamento, int unidade, String nomeCliente) {

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
    
    public LinkedList<Produto> getProdutos() {
        return this.produtos;
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

    public int getUnidade() {
        return this.unidade;
    }

    public void setUnidade(int unidade) {
        this.unidade = unidade;
    }

    public String getNomeCliente() {
        return this.nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
}
