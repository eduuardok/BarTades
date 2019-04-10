package com.bartades.controller;

import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

public class FranquiaController {
    
	public static ArrayList<Produto> encontrarProdutoPorId(int id) throws ClassNotFoundException, SQLException{
		return ProdutoDAO.encontrarProdutoPorId(id);
	}
	
    public static boolean SalvarProduto(String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor) 
            throws ClassNotFoundException, SQLException{
        
        Produto produto = new Produto(nome, descricao, categoria, precoVenda, precoCompra, fornecedor, 0);
        return ProdutoDAO.SalvarProduto(produto);
        
    }
    
    public static ArrayList<Produto> listarProdutos() throws ClassNotFoundException, SQLException{
        return ProdutoDAO.listarProdutos();
    }
    
    public static boolean AtualizarProduto(int id, String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor, int quantidade) 
            throws ClassNotFoundException, SQLException{
        
        Produto p = new Produto(id, nome, descricao, categoria, precoVenda, precoCompra, fornecedor, quantidade);
        
        return ProdutoDAO.AtualizarProduto(p);
    }
    
}
