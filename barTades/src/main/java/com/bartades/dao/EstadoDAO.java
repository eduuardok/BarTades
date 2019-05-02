/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.dao;


import com.bartades.model.Estado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antonio Carlos
 */
public class EstadoDAO {
	
    public static ArrayList<Estado> listarEstados() throws ClassNotFoundException, SQLException{
        
        ArrayList<Estado> listarEstados = new ArrayList<Estado>();
        
        String sql = "select * from estado;";
        
        try(Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()){
            
            while (retorno.next()){
            	Estado e = new Estado(
            			retorno.getInt("id"),
            			retorno.getString("nome"));
            			listarEstados.add(e);
            }
        }   
        return listarEstados;
    }
    

}