/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.dao;

import com.bartades.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELuna
 */
public class ProdutoDAO {
    
    public static ArrayList<Produto> listarProdutos() throws ClassNotFoundException, SQLException{
        
        ArrayList<Produto> listaProdutos = new ArrayList();
        
        String sql = "select p.id,\n" +
                     "p.nome, \n" +
                     "p.descricao, \n" +
                     "c.nome as categoria, \n" +
                     "p.preco_venda, \n" +
                     "p.preco_compra, \n" +
                     "f.nome as fornecedor \n" +
                     "from produtos p\n" +
                     "join categoria c on p.categoria = c.id\n" +
                     "join fornecedores f on p.id_fornecedor = f.id;";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()){
            
            while (retorno.next()){
                Produto p = new Produto(
                retorno.getInt("ID"),
                retorno.getString("nome"),
                retorno.getString("descricao"),
                retorno.getString("categoria"),
                retorno.getDouble("precoVenda"),
                retorno.getDouble("precoCompra"),
                retorno.getString("fornecedor"));
                listaProdutos.add(p);
            }
            
        }
                
        return listaProdutos;
    }
    
    public static boolean SalvarProduto(Produto p) throws ClassNotFoundException, SQLException{
        
        boolean retorno = false;
        
        String sql = "INSERT INTO produto (nome, descricao, categoria, preco_venda, preco_compra, fornecedor) values (?, ?, ?, ?, ?, ?);";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);
                ) {
            insert.setString(1, p.getNome());
            insert.setString(2, p.getDescricao());
            insert.setInt(3, encontrarIdCategoria(p.getCategoria()));
            insert.setDouble(4, p.getPrecoVenda());
            insert.setDouble(5, p.getPrecoCompra());
            insert.setInt(6, encontrarIdFornecedor(p.getFornecedor()));
            
            int linhasAfetadas = insert.executeUpdate();
            
            if(linhasAfetadas > 0){
                retorno = true;
            }
            
        }
        return retorno;
    }
    
    public static boolean AtualizarProduto(Produto p) throws ClassNotFoundException, SQLException{
        
        boolean retorno = false;
        
        String sql = "UPDATE produto set nome = ?, descricao = ?, categoria = ?, preco_venda = ?, preco_compra = ?, fornecedor = ? WHERE id = ?;";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement update = conn.prepareStatement(sql);
                ) {
            update.setString(1, p.getNome());
            update.setString(2, p.getDescricao());
            update.setInt(3, encontrarIdCategoria(p.getCategoria()));
            update.setDouble(4, p.getPrecoVenda());
            update.setDouble(5, p.getPrecoCompra());
            update.setInt(6, encontrarIdFornecedor(p.getFornecedor()));
            update.setInt(7, p.getId());
            
            int linhasAfetadas = update.executeUpdate();
            
            if(linhasAfetadas > 0){
                retorno = true;
            }
            
        }
        return retorno;
    }
    
    private static int encontrarIdCategoria(String nomeCategoria) throws ClassNotFoundException, SQLException{
        
        String sql = "SELECT id FROM categoria WHERE nome = ?";
        
        int idCategoria = 0;
        
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ){
                select.setString(1, nomeCategoria);
                ResultSet retorno = select.executeQuery();
                
                while(retorno.next()){
                idCategoria = retorno.getInt("id");
            }
            
        }
        
        return idCategoria;
    }
    
    private static int encontrarIdFornecedor(String nomeFornecedor) throws ClassNotFoundException, SQLException{
        
        String sql = "SELECT id FROM fornecedor WHERE nome = ?";
        
        int idFornecedor = 0;
        
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ){
                select.setString(1, nomeFornecedor);
                ResultSet retorno = select.executeQuery();
                
                while(retorno.next()){
                idFornecedor = retorno.getInt("id");
            }
            
        }
        
        return idFornecedor;
    }
    
}
