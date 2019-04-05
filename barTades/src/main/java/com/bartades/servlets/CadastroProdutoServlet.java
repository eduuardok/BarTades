package com.bartades.servlets;

import com.bartades.controller.ProdutoController;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CadastroProduto")
public class CadastroProdutoServlet extends HttpServlet {
    
        private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
            
            String nomeProduto = request.getParameter("nomeProduto");
            String categoriaProduto = request.getParameter("categoriaProduto");
            String fornecedorProduto = request.getParameter("fornecedorProduto");
            String valorCompra = request.getParameter("valorCompraProduto");
            String valorVenda = request.getParameter("valorVendaProduto");
            String descricaoProduto = request.getParameter("descricaoProduto");
            
            request.setAttribute("metodoHttp", metodoHttp);
            request.setAttribute("nomeProduto", nomeProduto);
            request.setAttribute("categoriaProduto", categoriaProduto);
            request.setAttribute("fornecedorProduto", fornecedorProduto);
            request.setAttribute("valorCompra", valorCompra);
            request.setAttribute("valorVenda", valorVenda);
            request.setAttribute("descricaoProduto", descricaoProduto);
            
            System.out.println(nomeProduto);
            
           RequestDispatcher dispatcher = request.getRequestDispatcher("Sucesso.jsp");
           dispatcher.forward(request, response);
            
        }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);
                    
               
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
            
            processarRequisicao("POST", request, response);
		
	}

}
