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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Antonio Carlos
 */
@WebServlet(name = "FornecedoresServlet", urlPatterns = {"/FornecedoresServlet"})
public class FornecedoresServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("Fornecedores.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estado");

        // Cria um novo contato e salva
        // através do DAO
        Fornecedores nova = new Fornecedores(0, nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado);
        FornecedoresDAO dao = new FornecedoresDAO();
        dao.incluirComTransacao(nova);

        // Usa a sessao para manter os dados após
        // redirect (técnica POST-REDIRECT-GET),
        // usado para evitar dupla submissão dos
        // dados
        HttpSession sessao = request.getSession();
        sessao.setAttribute("novaFornecedor", nova);
        response.sendRedirect("bootstrap/Fornecedor.jsp?gravou");

    }

}
