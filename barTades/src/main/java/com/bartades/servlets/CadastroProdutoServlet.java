package com.bartades.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/CadastroProduto"})
public class CadastroProdutoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String nomeProduto = request.getParameter("nomeProduto");
		String categoriaProduto = request.getParameter("categoriaProduto");
		String fornecedorProduto = request.getParameter("fornecedorProduto");
		String valorCompra = request.getParameter("valorCompraProduto");
		String valorVenda = request.getParameter("valorVendaProduto");
		
		request.setAttribute("nome", nomeProduto);
		
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		String nomeProduto = request.getParameter("nomeProduto");
		//String categoriaProduto = request.getParameter("categoriaProduto");
		//String fornecedorProduto = request.getParameter("fornecedorProduto");
		//String valorCompra = request.getParameter("valorCompraProduto");
		//String valorVenda = request.getParameter("valorVendaProduto");
		
		request.setAttribute("nome", nomeProduto);
		
		request.getRequestDispatcher("Sucesso.jsp").forward(request, response);
		
	}

}
