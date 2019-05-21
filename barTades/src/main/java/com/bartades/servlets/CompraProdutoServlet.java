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


@WebServlet(name = "ComprarProduto", urlPatterns = "/compraProduto")
public class CompraProdutoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ClassNotFoundException, SQLException {

		
		ArrayList<Produto> produtos = new ArrayList<Produto>();
		
		
		int qtdeProdutos = Integer.parseInt(request.getParameter("qtdeProdutos"));
		
		for(int i = 0; i < qtdeProdutos; i++) {
			Produto p = new Produto(request.getParameter("produto"+i), Integer.parseInt(request.getParameter("quantidadeProduto"+i)));
			System.out.println(p.getNome() + ", " + p.getQuantidade());
			produtos.add(p);
		}
		
		
		response.sendRedirect("visualizarProdutos");

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		try {
			//ArrayList<Produto> listaProdutos = ProdutoDAO.listarProdutos();
			ArrayList<Categoria> listaCategorias = CategoriaDAO.listarCategorias();
			
			//request.setAttribute("listaProdutos", listaProdutos);
			request.setAttribute("listaCategorias", listaCategorias);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("action", "compraProduto");
		request.setAttribute("pagina", "COMPRA DE PRODUTOS");
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroPedidoCompra.jsp").forward(request, response);

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
