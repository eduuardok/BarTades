package com.bartades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bartades.model.Categoria;

public class CategoriaDAO {
	
    public static ArrayList<Categoria> listarCategorias() throws ClassNotFoundException, SQLException{
        
        ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();
        
        String sql = "select * from categoria;";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()){
            
            while (retorno.next()){
            	Categoria c = new Categoria(
            			retorno.getInt("id"),
            			retorno.getString("nome"));
            			listaCategorias.add(c);
            }
        }   
        return listaCategorias;
    }
    

}
