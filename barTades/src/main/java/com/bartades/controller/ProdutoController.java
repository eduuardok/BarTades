/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.controller;

import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELuna
 */
public class ProdutoController {
    
    /**
     * Este método salva um objeto do tipo Produto no banco de dados
     * 
     * @param nome = String nome do produto
     * @param descricao = String descricao do produto
     * @param categoria = String categoria do produto
     * @param precoVenda = double preco venda do produto
     * @param precoCompra = double preco compra do produto
     * @param fornecedor = String fornecedor do produto
     * @return boolean = true: sucesso, false: falha
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
	
	public static ArrayList<Produto> encontrarProdutoPorId(int id) throws ClassNotFoundException, SQLException{
		return ProdutoDAO.encontrarProdutoPorId(id);
	}
	
    public static boolean SalvarProduto(String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor, boolean disponibilidade) 
            throws ClassNotFoundException, SQLException{
        
        Produto produto = new Produto(nome, descricao, categoria, precoVenda, precoCompra, fornecedor, 0, disponibilidade);
        return ProdutoDAO.SalvarProduto(produto);
        
    }
    
    /**
     * Este método lista todos os produtos no banco de dados
     * 
     * @return ArrayList do tipo Produto
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static ArrayList<Produto> listarProdutos() throws ClassNotFoundException, SQLException{
        return ProdutoDAO.listarProdutos();
    }
    
    /**
     * Este método atualiza um produto já existente no banco de dados
     * 
     * @param id = int id do produto
     * @param nome = String nome do produto
     * @param descricao = String descricao do produto
     * @param categoria = String categoria do produto
     * @param precoVenda = double preco venda do produto
     * @param precoCompra = double preco compra do produto
     * @param fornecedor = String fornecedor do produto
     * @param quantidade = int quantidade a ser adicionada ao produto
     * @return boolean = true: sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static boolean AtualizarProduto(int id, String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor, int quantidade, boolean disponibilidade) 
            throws ClassNotFoundException, SQLException{
        
        Produto p = new Produto(id, nome, descricao, categoria, precoVenda, precoCompra, fornecedor, quantidade, disponibilidade);
        
        return ProdutoDAO.AtualizarProduto(p);
    }
    
}
