/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bartades.controller.UsuarioController;
import com.bartades.model.Usuario;

/**
 *
 * @author Victor
 */
@WebServlet(name = "VisualizarUsuarios", urlPatterns = "/visualizarUsuarios")
public class ListarUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			HttpSession sessao = request.getSession();
			
			Usuario user = (Usuario) sessao.getAttribute("usuario");
			
			
			ArrayList<Usuario> listaUsuarios = UsuarioController.listarUsuarios();
			ArrayList<Usuario> listaFiltrada = new ArrayList<Usuario>();

			if(user.getNivelAcesso() <= 4) {
				for(Usuario u : listaUsuarios) {
					if(u.getUnidadeAtuacao().equals(user.getUnidadeAtuacao())) {
						listaFiltrada.add(u);
					}
				}
				request.setAttribute("listaDeUsuarios", listaFiltrada);
			} else {
				request.setAttribute("listaDeUsuarios", listaUsuarios);
			}
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Usuario.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			List<Usuario> listaUsuarios = UsuarioController.listarUsuarios();

			request.setAttribute("listaDeUsuarios", listaUsuarios);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/Usuario.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
