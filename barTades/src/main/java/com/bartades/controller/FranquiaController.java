package com.bartades.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import com.bartades.dao.FranquiaDAO;
import com.bartades.model.Franquia;

public class FranquiaController {
	
    public static boolean SalvarFranquia(String nome, String estado, String endereco) 
            throws ClassNotFoundException, SQLException{
        
        Franquia franquia = new Franquia(nome, estado, endereco);
        return FranquiaDAO.SalvarFranquia(franquia);
        
    }
    
    public static ArrayList<Franquia> listarFranquias() throws ClassNotFoundException, SQLException{
        return FranquiaDAO.listarFranquias();
    }
    
    public static boolean AtualizarFranquia(int id, String nome, String estado, String endereco) 
            throws ClassNotFoundException, SQLException{
        
        Franquia f = new Franquia(id, nome, estado, endereco);
        
        return FranquiaDAO.AtualizarFranquia(f);
    }
    
}
