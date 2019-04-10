package com.bartades.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.model.Franquia;

@WebServlet(urlPatterns = "/cadastroFranquia")
public class CadastroFranquiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		request.getRequestDispatcher("Franquia.jsp");

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String nome = request.getParameter("nomeFranquia");
		String endereco = request.getParameter("enderecoFranquia");
		String estado = request.getParameter("estadoFranquia");

		Franquia franquia = new Franquia(nome, estado, endereco);

		request.getRequestDispatcher("Franquia.jsp").forward(request, response);

	}

}
