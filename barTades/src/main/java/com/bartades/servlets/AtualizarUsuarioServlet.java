package com.bartades.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.controller.UsuarioController;
import com.bartades.model.Usuario;

@WebServlet(name = "EditarUsuario", urlPatterns = "/editarUsuario")
public class AtualizarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {
		
		String nome = request.getParameter("nomeUsuario");
		String cpf = request.getParameter("cpfUsuario");
		String email = request.getParameter("emailUsuario");
		String senha = request.getParameter("senhaUsuario");
		String telefone = request.getParameter("telefoneUsuario");
		String sexo = request.getParameter("sexoUsuario");
		String cargo = request.getParameter("nivelAcessoUsuario");
		String unidadeAtuacao = request.getParameter("unidadeAtuacaoUsuario");
		int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
		
		Usuario user = new Usuario(idUsuario, nome, cpf, email, senha, telefone, sexo, unidadeAtuacao, cargo);

		UsuarioController.atualizarUsuario(user);
		
		response.sendRedirect("visualizarUsuarios");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("idUsuario"));
		request.setAttribute("pagina", "ATUALIZAR USUARIO");
		try {
			ArrayList<Usuario> usuario = UsuarioController.encontrarUsuarioPorId(id);
			request.setAttribute("nomeUsuario", usuario.get(0).getNome());
			request.setAttribute("emailUsuario", usuario.get(0).getEmail());
			request.setAttribute("telefoneUsuario", usuario.get(0).getTelefone());
			request.setAttribute("cpfUsuario", usuario.get(0).getCPF());
			request.setAttribute("sexoUsuario", usuario.get(0).getSexo());
			request.setAttribute("unidadeAtuacaoUsuario", usuario.get(0).getUnidadeAtuacao());
			request.setAttribute("cargoUsuario", usuario.get(0).getCargo());
			request.setAttribute("idUsuario", usuario.get(0).getId());
			request.setAttribute("action", "editarUsuario");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/WEB-INF/jsp/CadastroUsuario.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		try {
			processarRequisicao("POST", request, response);
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}

}
