package com.bartades.servlets;

import com.bartades.dao.EstadoDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.dao.FranquiaDAO;
import com.bartades.model.Estado;
import com.bartades.model.Fornecedores;
import com.bartades.model.Franquia;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio Carlos
 */
@WebServlet(urlPatterns = "/editarFranquia")
public class AtualizarFranquiaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void limparForm(HttpServletRequest request, HttpServletResponse response) {

    }

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
        String idFranquia = request.getParameter("idFranquia");
        boolean disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidadeFranquia"));

        Franquia franquia = new Franquia(Integer.parseInt(idFranquia), nome, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);

        FranquiaDAO.AtualizarFranquia(franquia);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("visualizarFranquia");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("idFranquia"));
        request.setAttribute("pagina", "ATUALIZAR FRANQUIA");
        try {
            ArrayList<Franquia> franquia = FranquiaDAO.encontrarFranquiaPorId(id);
            request.setAttribute("nome", franquia.get(0).getNome());
            request.setAttribute("endereco", franquia.get(0).getEndereco());
            request.setAttribute("numero", franquia.get(0).getNumero());
            request.setAttribute("complemento", franquia.get(0).getComplemento());
            request.setAttribute("cep", franquia.get(0).getCep());
            request.setAttribute("bairro", franquia.get(0).getBairro());
            request.setAttribute("cidade", franquia.get(0).getCidade());
            request.setAttribute("estadoFranquia", franquia.get(0).getEstado());
            request.setAttribute("disponibilidadeFranquia", franquia.get(0).isDisponibilidade());
            request.setAttribute("idFranquia", id);
            request.setAttribute("action", "editarFranquia");
            ArrayList<Estado> listaEstados = EstadoDAO.listarEstados();
            for (int i = 0; i < listaEstados.size(); i++) {
                if (listaEstados.get(i).getNome().equals(franquia.get(0).getEstado())) {
                    listaEstados.remove(i);
                }
            }
            request.setAttribute("listarEstados", listaEstados);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/WEB-INF/jsp/CadastroFranquia.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        try {
            processarRequisicao("POST", request, response);
        } catch (ClassNotFoundException | SQLException e) {

            e.printStackTrace();
        }

    }

}
