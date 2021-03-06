/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.servlets;

/**
 *
 * @author Antonio Carlos
 */


import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Fornecedores;
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


@WebServlet(urlPatterns = "/visualizarFornecedor", name="visualizarFornecedor")
public class ListarFornecedoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List<Fornecedores> listarFornecedores = FornecedoresDAO.listarFornecedores();
			request.setAttribute("listaDeFornecedores", listarFornecedores);	
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Fornecedor.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarFornecedoresServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
}
	
}

