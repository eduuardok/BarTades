/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.controller;

import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Produto;
import java.sql.SQLException;

/**
 *
 * @author ELuna
 */
public class ProdutoController {
    
    public static boolean SalvarProduto(String nome, String descricao, String categoria, double precoVenda, double precoCompra, String fornecedor) 
            throws ClassNotFoundException, SQLException{
        
        Produto produto = new Produto(nome, descricao, categoria, precoVenda, precoCompra, fornecedor);
        return ProdutoDAO.SalvarProduto(produto);
        
    }
    
}
