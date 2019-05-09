/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ELuna
 */
public class InterfaceConexao {
    
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/bartades?useTimezone=true&serverTimezone=UTC";
    private final static String USUARIO = "root";
    private final static String SENHA = "victor123"; //<-- lembrar de trocar testebase1
    
    
    protected static Connection obterConexao() throws ClassNotFoundException, SQLException{
        
        Class.forName(DRIVER);
        
        Connection conn = DriverManager.getConnection(URL, USUARIO, SENHA);
        
        return conn;
    }
    
    
}
