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
import com.bartades.model.Produto;

@WebServlet(urlPatterns = "/editarProduto")
public class AtualizarProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		String nomeProduto = request.getParameter("nomeProduto");
		String categoriaProduto = request.getParameter("categoriaProduto");
		String fornecedorProduto = request.getParameter("fornecedorProduto");
		String valorCompra = request.getParameter("valorCompraProduto").replace(".", "").replace(",", ".");
		String valorVenda = request.getParameter("valorVendaProduto").replace(".", "").replace(",", ".");
		String descricaoProduto = request.getParameter("descricaoProduto");
		String idProduto = request.getParameter("idProduto");
		boolean disponibilidadeProduto = Boolean.parseBoolean(request.getParameter("disponibilidadeProduto"));
		
		ProdutoController.AtualizarProduto(Integer.parseInt(idProduto), nomeProduto, descricaoProduto, categoriaProduto, Double.parseDouble(valorVenda),
				Double.parseDouble(valorCompra), fornecedorProduto, 0, disponibilidadeProduto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("visualizarProdutos");
		dispatcher.forward(request, response);

		

	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		int id = Integer.parseInt(request.getParameter("idProduto"));
		request.setAttribute("pagina", "ATUALIZAR PRODUTO");
		try {
			ArrayList<Produto> produto = ProdutoController.encontrarProdutoPorId(id);
			request.setAttribute("nomeProduto", produto.get(0).getNome());
			request.setAttribute("categoriaProduto", produto.get(0).getCategoria());
			request.setAttribute("precoVenda", produto.get(0).getPrecoVenda());
			request.setAttribute("precoCompra", produto.get(0).getPrecoCompra());
			request.setAttribute("fornecedorProduto", produto.get(0).getFornecedor());
			request.setAttribute("descricaoProduto", produto.get(0).getDescricao());
			request.setAttribute("disponibilidadeProduto", produto.get(0).isDisponivel());
			request.setAttribute("idProduto", id);
			request.setAttribute("action", "editarProduto");
			ArrayList<Categoria> listaCategorias = CategoriaController.listarCategorias();
			request.setAttribute("listaCategorias", listaCategorias);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);

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
