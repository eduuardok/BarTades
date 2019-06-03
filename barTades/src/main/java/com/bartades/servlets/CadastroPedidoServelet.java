package com.bartades.servlets;

import com.bartades.dao.CategoriaDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Categoria;
import com.bartades.model.Fornecedores;
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

import com.bartades.dao.ProdutoDAO;
import com.bartades.model.Produto;
import com.bartades.model.Franquia;
import com.bartades.dao.FranquiaDAO;
import com.bartades.dao.PedidoDAO;
import com.bartades.model.Pedido;
import java.util.LinkedList;
import java.util.Set;

@WebServlet(name = "CadastrarPedido", urlPatterns = "/CadastroPedido")
public class CadastroPedidoServelet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private LinkedList<Produto> produtos;

    private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, NumberFormatException {

        Pedido p;
        p = new Pedido(request.getParameter("tipoPagamento"), Integer.valueOf(request.getParameter("unidade")), request.getParameter("emailUsuario"));
        int i = 1, idPedido;
        Produto NewP;

        while (i > 0) {

            idPedido = -1;
            
            try {
                idPedido = Integer.valueOf(request.getParameter("produtos" + i));
            } catch (NumberFormatException ex) {
                Logger.getLogger(CadastroPedidoServelet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if (idPedido >= 0) {
                NewP = ProdutoDAO.encontrarProdutoPorId(idPedido).get(0);
                NewP.setQuantidade(Integer.valueOf(request.getParameter("quantidade" + i)));
                p.adicionarProduto(NewP);
                i = i +1;
            } else {
                i = 0;
            }
            
            

        }
        
        PedidoDAO.SalvarPedido(p);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            processarRequisicao("POST", request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroPedidoServelet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ArrayList<Categoria> listaCategorias = null;
        ArrayList<Franquia> listaUnidades = null;

        try {
            listaCategorias = CategoriaDAO.listarCategorias();
            request.setAttribute("listaCategorias", listaCategorias);
        } catch (ClassNotFoundException | SQLException e) {
        }

        try {
            listaUnidades = FranquiaDAO.listarFranquias();
            request.setAttribute("listaUnidades", listaCategorias);
        } catch (ClassNotFoundException | SQLException e) {
        }

        request.setAttribute("action", "CadastroPedido");
        request.setAttribute("pagina", "CADASTRO DE PEDIDO");
        request.getRequestDispatcher("WEB-INF/jsp/CadastroPedido.jsp").forward(request, response);

    }

}
