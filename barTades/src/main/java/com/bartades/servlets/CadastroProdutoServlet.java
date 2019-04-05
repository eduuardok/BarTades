package com.bartades.servlets;

import com.bartades.controller.ProdutoController;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/CadastroProduto")
public class CadastroProdutoServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		//String nomeProduto = request.getParameter("nomeProduto");
		String categoriaProduto = request.getParameter("categoriaProduto");
		String fornecedorProduto = request.getParameter("fornecedorProduto");
		String valorCompra = request.getParameter("valorCompraProduto");
		String valorVenda = request.getParameter("valorVendaProduto");
		
		//request.setAttribute("nome", nomeProduto);
		
		request.getRequestDispatcher("CadastroProduto.jsp").forward(request, response);
                    
               
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
            
            String nomeProduto = request.getParameter("nomeProduto");
                String categoriaProduto = request.getParameter("categoriaProduto");
                String fornecedorProduto = request.getParameter("fornecedorProduto");
                String valorCompra = request.getParameter("valorCompraProduto");
                String valorVenda = request.getParameter("valorVendaProduto");
                
                System.out.println(nomeProduto);
                System.out.println(categoriaProduto);
                System.out.println(fornecedorProduto);
                System.out.println(valorCompra);
                System.out.println(valorVenda);
		
            try {
                
 
            ProdutoController.SalvarProduto(request.getParameter("nomeProduto"), request.getParameter("categoriaProduto"),
                        request.getParameter("descricaoProduto"), Double.parseDouble(request.getParameter("valorVendaProduto")), Double.parseDouble(request.getParameter("valorCompraProduto")), request.getParameter("fornecedorProduto"));
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(CadastroProdutoServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
		
		request.setAttribute("nome", request.getParameter("nomeProduto"));
		
		request.getRequestDispatcher("/WEB-INF/Sucesso.jsp").forward(request, response);
		
	}

}
