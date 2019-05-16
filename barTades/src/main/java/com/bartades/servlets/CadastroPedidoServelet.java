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
import java.util.LinkedList;
import java.util.Set;

@WebServlet(name = "CadastrarPedido", urlPatterns = "/CadastroPedido")
public class CadastroPedidoServelet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private LinkedList<Produto> produtos;

    private void newProduto(String classeProduto) {

    }

    private ArrayList<Produto> obterProdutosPorCategoria(String categoria) throws ClassNotFoundException, ClassNotFoundException, ClassNotFoundException, SQLException {

        ArrayList<Produto> listaProdutos = ProdutoDAO.listarProdutos();
        ArrayList<Produto> listaProdutoCategoria = new ArrayList<Produto>();

        for (Produto p : listaProdutos) {
            if (p.getCategoria().equals(categoria)) {
                listaProdutoCategoria.add(p);
            }
        }
        return listaProdutoCategoria;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ArrayList<Categoria> listaCategorias = null;
        ArrayList<Produto> listaProdutos = null;

        try {
            listaCategorias = CategoriaDAO.listarCategorias();
            request.setAttribute("listaCategorias", listaCategorias);
            listaProdutos = obterProdutosPorCategoria(listaCategorias.get(0).getNome());
            request.Set<String> hashSet = new HashSet<String>();
            
            for (Produto p : listaProdutos) {
                System.out.println(p.getNome());
            }

        } catch (ClassNotFoundException | SQLException e) {
        }


        request.setAttribute("action", "CadastroPedido");
        request.setAttribute("pagina", "CADASTRO DE PEDIDO");
        request.getRequestDispatcher("WEB-INF/jsp/CadastroPedido.jsp").forward(request, response);

    }
}
