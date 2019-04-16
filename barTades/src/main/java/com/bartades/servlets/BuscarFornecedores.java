package com.bartades.servlets;

import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Fornecedores;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio
 */
@WebServlet(name = "BuscarFornecedores", urlPatterns = {"/buscarFornecedor"})
public class BuscarFornecedores extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String b = request.getParameter("buscarCnpj");

        FornecedoresDAO dao = new FornecedoresDAO();
        try {
            Fornecedores lista = dao.obterFornecedores(b);
            request.setAttribute("listaFornecedores", lista);
        } catch (Exception e) {
            System.out.println("Erro");
        }

        RequestDispatcher dispatcher
                = request.getRequestDispatcher("buscarFornecedor.jsp");
        dispatcher.forward(request, response);

        try {
            dispatcher.forward(request, response);
        } catch (IOException ex) {

        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String bt1 = request.getParameter("btAlterar");
        String bt2 = request.getParameter("btExcluir");

        FornecedoresDAO forn = new FornecedoresDAO();
        Fornecedores fornecedor = new Fornecedores();
        fornecedor.setID(Integer.parseInt(request.getParameter("codigoBusca")));
        fornecedor.setNome(request.getParameter("nome"));
        fornecedor.setTelefone(request.getParameter("telefoneBusca"));
        fornecedor.setEndereco(request.getParameter("enderecoBusca"));
        fornecedor.setNumero(request.getParameter("numeroBusca"));
        fornecedor.setComplemento(request.getParameter("complementoBusca"));
        fornecedor.setCep(request.getParameter("cepBusca"));
        fornecedor.setBairro(request.getParameter("bairroBusca"));
        fornecedor.setCidade(request.getParameter("cidadeBusca"));
        fornecedor.setEstado(request.getParameter("estadoBusca"));

        try {
            if (bt1 != null) {
                forn.atualizar(fornecedor);
            } else if (bt2 != null) {
                forn.remover(Integer.parseInt(request.getParameter("codigoBusca")));
            }
        } catch (Exception e) {
            System.out.println("Erro");
        }

        request.getRequestDispatcher("bootstrap/buscarFornecedor.jsp").forward(request, response);
        //response.sendRedirect("bootstrap/cliente.jsp");

    }

}
