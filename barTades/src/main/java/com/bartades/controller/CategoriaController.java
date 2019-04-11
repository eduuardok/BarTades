package com.bartades.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import com.bartades.dao.CategoriaDAO;
import com.bartades.model.Categoria;

public class CategoriaController {

	public static ArrayList<Categoria> listarCategorias() throws ClassNotFoundException, SQLException{
        return CategoriaDAO.listarCategorias();
    }
    
}
