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
import com.bartades.dao.CategoriaDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.dao.FranquiaDAO;
import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Categoria;
import com.bartades.model.Fornecedores;
import com.bartades.model.Franquia;
import com.bartades.model.Produto;

/**
 * 
 * @author ELuna
 *
 */
@WebServlet(name = "CadastrarProduto", urlPatterns = "/cadastroProduto")
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
		String unidadeProduto = request.getParameter("unidadeProduto");
		
		Produto p = new Produto(nomeProduto, descricaoProduto, categoriaProduto, Double.parseDouble(valorVenda),
				Double.parseDouble(valorCompra), fornecedorProduto, 0, disponibilidadeProduto, unidadeProduto);

		ProdutoDAO.SalvarProduto(p);

		response.sendRedirect("visualizarProdutos");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		
		try {
			ArrayList<Categoria> listaCategorias = CategoriaDAO.listarCategorias();
			ArrayList<Fornecedores> listaFornecedores = FornecedoresDAO.listar();
			ArrayList<Franquia> listaUnidades = FranquiaDAO.listarFranquias();
			request.setAttribute("listaCategorias", listaCategorias);
			request.setAttribute("listaFornecedores", listaFornecedores);
			request.setAttribute("listaUnidades", listaUnidades);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("action", "cadastroProduto");
		request.setAttribute("pagina", "CADASTRO DE PRODUTO");
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroProduto.jsp").forward(request, response);

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
