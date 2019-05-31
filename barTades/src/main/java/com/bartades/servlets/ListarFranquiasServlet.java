/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.servlets;

import com.bartades.dao.FranquiaDAO;
import com.bartades.model.Franquia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Carlos
 */
@WebServlet(urlPatterns = "/visualizarFranquia")
public class ListarFranquiasServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Franquia> listarFranquias = FranquiaDAO.listarFranquias();
			request.setAttribute("listaDeFranquias", listarFranquias);	
			RequestDispatcher rd = request.getRequestDispatcher("Franquia.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarFranquiasServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
}
	
}
