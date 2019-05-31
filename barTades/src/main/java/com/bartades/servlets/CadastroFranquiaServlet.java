package com.bartades.servlets;

import com.bartades.dao.EstadoDAO;
import com.bartades.dao.FranquiaDAO;
import com.bartades.model.Estado;
import com.bartades.model.Franquia;
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

@WebServlet(urlPatterns = "/cadastroFranquia")
public class CadastroFranquiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private void processarRequisicao(String metodoHttp, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String numero = request.getParameter("numero");
        String complemento = request.getParameter("complemento");
        String cep = request.getParameter("cep");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("estadoFranquia");
        boolean disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidadeFranquia"));
        Franquia franquia = new Franquia(nome, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);
        FranquiaDAO.SalvarFranquia(franquia);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("visualizarFranquia");

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
        request.setAttribute("action", "cadastroFranquia");
        request.setAttribute("pagina", "CADASTRO DE FRANQUIA");
        request.getRequestDispatcher("/WEB-INF/jsp/CadastroFranquia.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            processarRequisicao("POST", request, response);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CadastroFranquiaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
