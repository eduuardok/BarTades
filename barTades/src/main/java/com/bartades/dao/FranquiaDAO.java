package com.bartades.dao;


import com.bartades.model.Franquia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class FranquiaDAO {

	
    public static ArrayList<Franquia> listarFranquias() throws ClassNotFoundException, SQLException{
        
        ArrayList<Franquia> listaFranquias = new ArrayList<Franquia>();
        
        String sql = "select * from unidades;";
        
        try(Connection conn = com.bartades.dao.InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()){
            
            while (retorno.next()){
            	Franquia f = new Franquia(
            			retorno.getInt("id"),
            			retorno.getString("nome"),
            			retorno.getString("estado"),
            			retorno.getString("endereco"));
            			listaFranquias.add(f);
            }
        }   
        return listaFranquias;
    }
    
    public static boolean SalvarFranquia(Franquia f) throws ClassNotFoundException, SQLException{
        
        boolean retorno = false;
        
        String sql = "INSERT INTO unidades (nome, endereco, estado) values (?, ?, ?);";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);
                ) {
            insert.setString(1, f.getNome());
            insert.setString(2, f.getEndereco());
            insert.setString(3, f.getEstado());
            
            int linhasAfetadas = insert.executeUpdate();
            
            if(linhasAfetadas > 0){
                retorno = true;
            }
        }
        return retorno;
    }
    
    public static boolean AtualizarFranquia(Franquia f) throws ClassNotFoundException, SQLException{
        
        boolean retorno = false;
        
        String sql = "UPDATE unidades set nome = ?, endereco = ?, estado = ? WHERE id = ?;";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement update = conn.prepareStatement(sql);
                ) {
            update.setString(1, f.getNome());
            update.setString(2, f.getEndereco());
            update.setString(3, f.getEstado());
            update.setInt(4, f.getId());
            
            int linhasAfetadas = update.executeUpdate();
            
            if(linhasAfetadas > 0){
                retorno = true;
            }   
        }
        return retorno;
    }
    
}
    