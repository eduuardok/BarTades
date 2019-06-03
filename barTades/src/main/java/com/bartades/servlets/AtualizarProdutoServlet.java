package com.bartades.servlets;

import java.awt.List;
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
import javax.servlet.http.HttpSession;

import com.bartades.dao.CategoriaDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.dao.FranquiaDAO;
import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Categoria;
import com.bartades.model.Fornecedores;
import com.bartades.model.Franquia;
import com.bartades.model.Produto;
import com.bartades.model.Usuario;

/**
 * 
 * @author ELuna
 *
 */
@WebServlet(name = "AtualizarProduto", urlPatterns = "/editarProduto")
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
		String unidadeProduto = request.getParameter("unidadeProduto");
		
		Produto p = new Produto(Integer.parseInt(idProduto), nomeProduto, descricaoProduto, categoriaProduto, Double.parseDouble(valorVenda),
				Double.parseDouble(valorCompra), fornecedorProduto, 0, disponibilidadeProduto, unidadeProduto);
		
		boolean testeModal = ProdutoDAO.AtualizarProduto(p);
		
		request.setAttribute("teste", testeModal);

		response.sendRedirect("visualizarProdutos");
		
	}
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		HttpSession sessao = request.getSession();
		
		Usuario u = (Usuario) sessao.getAttribute("usuario");

		int id = Integer.parseInt(request.getParameter("idProduto"));
		request.setAttribute("pagina", "ATUALIZAR PRODUTO");
		try {
			ArrayList<Produto> produto = ProdutoDAO.encontrarProdutoPorId(id);
			request.setAttribute("produto", (Produto) produto.get(0));
			request.setAttribute("action", "editarProduto");
			ArrayList<Categoria> listaCategorias = CategoriaDAO.listarCategorias();
			for(int i = 0; i < listaCategorias.size(); i++) {
				if(listaCategorias.get(i).getNome().equals(produto.get(0).getCategoria())) {
				listaCategorias.remove(i);
			}
			}
			ArrayList<Fornecedores> listaFornecedores = FornecedoresDAO.listar(); 
			for(int i = 0; i < listaFornecedores.size(); i++) {
				if(listaFornecedores.get(i).getNome().equals(produto.get(0).getFornecedor())) {
				listaFornecedores.remove(i);
			}
			}
			
			ArrayList<Franquia> listaUnidades = FranquiaDAO.listarFranquias();
			ArrayList<Franquia> listaUnidadesFiltrada = new ArrayList<Franquia>();
			
			for(int i = 0; i < listaUnidades.size(); i++) {
				if(listaUnidades.get(i).getNome().equals(produto.get(0).getUnidade())) {
				listaUnidades.remove(i);
				}
			}
			
			if(u.getNivelAcesso() <= 4) {
				for(Franquia f : listaUnidades) {
					if(f.getNome().equals(u.getUnidadeAtuacao())) {
						listaUnidadesFiltrada.add(f);
					}
				}
				request.setAttribute("listaUnidades", listaUnidadesFiltrada);
			} else {
				request.setAttribute("listaUnidades", listaUnidades);
			}
			
			request.setAttribute("listaFornecedores", listaFornecedores);
			request.setAttribute("listaCategorias", listaCategorias);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/CadastroProduto.jsp").forward(request, response);

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
