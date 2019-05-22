package com.bartades.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Produto;

@WebServlet(name = "ProdutoAjaxServlet", urlPatterns="/ProdutoAjaxServlet")
public class ProdutoAjaxServlet extends HttpServlet {

	
		private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		response.setContentType("text/plain");
		
		int categoriaPesquisa = 0;
		
		request.setCharacterEncoding("UTF-8");
			
		if(request.getParameter("categoria") != null) {
		
		categoriaPesquisa = Integer.parseInt(request.getParameter("categoria"));
		
		}
		
		int idProduto = 0;
		
		if(request.getParameter("produto") != null && !request.getParameter("produto").equals("undefined")) {
		idProduto = Integer.parseInt(request.getParameter("produto"));
		
		}
		PrintWriter out = response.getWriter();
		
		if(categoriaPesquisa != 0) {
		try {
			ArrayList<Produto> listaProdutos = ProdutoDAO.encontrarProdutoPorCategoria(categoriaPesquisa);
			
			String resposta = "";
			
			for(Produto p : listaProdutos) {
				resposta += p.getId() + ":" + p.getNome() + ",";
			}
			
			out.write(resposta);
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		if(idProduto != 0) {
			String respostaProduto = "";
			try {
				ArrayList<Produto> produtos = ProdutoDAO.listarProdutos();
				for(Produto p : produtos) {
					if(p.getId() == idProduto) {
						respostaProduto = String.valueOf(p.getPrecoCompra());
						out.write(respostaProduto);
					}
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
    }

}

