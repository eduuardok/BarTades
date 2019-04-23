package com.bartades.servlets;

import java.io.IOException;
import java.sql.SQLException;

import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Usuario;
import com.bartades.services.loginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException, ServletException {
		
		HttpSession sessao = request.getSession();
		
		String email = request.getParameter("emailUsuario");
		String senha = request.getParameter("senhaUsuario");
		
		if(loginService.realizarLogin(email, senha)) {
			Usuario usuarioLogin = UsuarioDAO.encontrarUsuarioPorEmail(email);
			sessao.setAttribute("loginValido", "true");
			sessao.setAttribute("usuario", usuarioLogin);
			response.sendRedirect("index.jsp");
		} else {
			sessao.setAttribute("loginInvalido", "true");
			response.sendRedirect("login.jsp");
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		request.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
			processarRequisicao(request, response);
		} catch (ClassNotFoundException | SQLException e) {
		
			e.printStackTrace();
		}

	}

}
