package com.bartades.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.controller.CategoriaController;
import com.bartades.controller.ProdutoController;
import com.bartades.model.Categoria;

@WebServlet(urlPatterns = "/cadastroProduto")
public class CadastroProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		String nomeProduto = request.getParameter("nomeProduto");
		String categoriaProduto = request.getParameter("categoriaProduto");
		String fornecedorProduto = request.getParameter("fornecedorProduto");
		String valorCompra = request.getParameter("valorCompraProduto").replace(".", "").replace(",", ".");
		String valorVenda = request.getParameter("valorVendaProduto").replace(".", "").replace(",", ".");
		String descricaoProduto = request.getParameter("descricaoProduto");
		boolean disponibilidadeProduto = Boolean.parseBoolean(request.getParameter("disponibilidadeProduto"));

		ProdutoController.SalvarProduto(nomeProduto, descricaoProduto, categoriaProduto, Double.parseDouble(valorVenda),
				Double.parseDouble(valorCompra), fornecedorProduto, disponibilidadeProduto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");
		dispatcher.forward(request, response);

		

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		
		try {
			ArrayList<Categoria> listaCategorias = CategoriaController.listarCategorias();
			request.setAttribute("listaCategorias", listaCategorias);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("action", "cadastroProduto");
		request.setAttribute("pagina", "CADASTRO DE PRODUTO");
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {
			processarRequisicao("POST", request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(CadastroProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

}
