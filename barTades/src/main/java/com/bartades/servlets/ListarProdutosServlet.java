/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.servlets;

import com.bartades.controller.ProdutoController;
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
 * @author ELuna
 */
@WebServlet(urlPatterns = "/visualizarProdutos")
public class ListarProdutosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("Produto.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			List listaProdutos = ProdutoController.listarProdutos();
			request.setAttribute("sessaoListaProdutos", listaProdutos);
			request.setAttribute("teste", "teste");
			RequestDispatcher rd = request.getRequestDispatcher("Produto.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
