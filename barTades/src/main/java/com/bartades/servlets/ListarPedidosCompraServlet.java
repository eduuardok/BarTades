package com.bartades.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bartades.dao.CompraProdutoDAO;
import com.bartades.dao.ProdutoDAO;
import com.bartades.model.PedidoCompraProduto;
import com.bartades.model.Produto;


@WebServlet(name = "VisualizarCompras", urlPatterns = "/visualizarPedidosCompra")
public class ListarPedidosCompraServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if(request.getParameter("idPedido") == null || request.getParameter("idPedido").equals("")) {
		try {

			ArrayList<PedidoCompraProduto> listaPedidos = CompraProdutoDAO.listarPedidosCompra();
			request.setAttribute("listaDePedidos", listaPedidos);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/PedidoCompra.jsp");
			rd.forward(request, response);
		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(ListarProdutosServlet.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	} else {
		int idPedido = Integer.parseInt(request.getParameter("idPedido"));
		try {
			
			PedidoCompraProduto pedido = CompraProdutoDAO.EncontrarPedidoCompraPorId(idPedido).get(0);
			ArrayList<Produto> listaGeral = ProdutoDAO.listarProdutos();
			pedido.encontrarNomeProdutos(listaGeral);
			request.setAttribute("pedido", pedido);
			
			request.getRequestDispatcher("/WEB-INF/jsp/VisualizacaoCompra.jsp").forward(request, response);;
			
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}



