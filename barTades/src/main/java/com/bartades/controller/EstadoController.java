/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.controller;

import com.bartades.dao.EstadoDAO;
import com.bartades.model.Estado;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antonio Carlos
 */
public class EstadoController {

    public static ArrayList<Estado> listarEstados() throws ClassNotFoundException, SQLException {
        return EstadoDAO.listarEstados();
    }
}
