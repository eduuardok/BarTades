package com.bartades.servlets;

import com.bartades.dao.EstadoDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Estado;
import com.bartades.model.Fornecedores;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Carlos
 */
@WebServlet(urlPatterns = {"/FornecedoresServlet"}, name="FornecedoresServlet")
public class FornecedoresServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        String nome = request.getParameter("nome");
        String cnpj = request.getParameter("cnpj");
        String telefone = request.getParameter("telefone");
        String endereco = request.getParameter("endereco");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estadoFornecedor");
        boolean disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidadeFornecedor"));
        Fornecedores fornecedor = new Fornecedores(nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);
        FornecedoresDAO.incluir(fornecedor);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("visualizarFornecedor");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            ArrayList<Estado> listarEstados = EstadoDAO.listarEstados();

            request.setAttribute("listarEstados", listarEstados);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("action", "FornecedoresServlet");
        request.setAttribute("pagina", "CADASTRO DE FORNECEDORES");
        request.getRequestDispatcher("/WEB-INF/jsp/CadastroFornecedores.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            processarRequisicao("POST", request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FornecedoresServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
