package com.bartades.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Usuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(urlPatterns =  "/cadastroUsuario" )
public class CadastroUsuarioServlet extends HttpServlet {
	//private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
            
            request.getRequestDispatcher("CadastroUsuario.jsp");
            
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String nome = request.getParameter("nomeUsuario");
		String cpf = request.getParameter("cpfUsuario");
		String email = request.getParameter("emailUsuario");
		String senha = request.getParameter("senhaUsuario");
		String telefone = request.getParameter("telefoneUsuario");
		String sexo = request.getParameter("sexoUsuario");
		String cargo = request.getParameter("nivelAcessoUsuario");
		String  unidadeAtuacao = request.getParameter("unidadeAtuacaoUsuario");
        
                System.out.println(nome);

		//request.setAttribute("nome", nome);

		request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
	}

}
