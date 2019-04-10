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

import com.bartades.controller.ProdutoController;
import com.bartades.model.Produto;

@WebServlet(urlPatterns = "/editarProduto")
public class AtualizarProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("idProduto"));
		try {
			ArrayList<Produto> produto = ProdutoController.encontrarProdutoPorId(id);
			request.setAttribute("nomeProduto", produto.get(0).getNome());
			request.setAttribute("categoriaProduto", produto.get(0).getCategoria());
			request.setAttribute("precoVenda", produto.get(0).getPrecoVenda());
			request.setAttribute("precoCompra", produto.get(0).getPrecoCompra());
			request.setAttribute("fornecedorProduto", produto.get(0).getFornecedor());
			request.setAttribute("descricaoProduto", produto.get(0).getDescricao());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		

	} 
	
	

}
