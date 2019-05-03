package com.bartades.servlets;

import com.bartades.dao.EstadoDAO;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.model.Estado;
import com.bartades.model.Fornecedores;
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
@WebServlet(urlPatterns = "/editarFornecedor")
public class AtualizarFornecedorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void limparForm(HttpServletRequest request, HttpServletResponse response) {

    }

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
        String idFornecedor = request.getParameter("idFornecedor");
        boolean disponibilidade = Boolean.parseBoolean(request.getParameter("disponibilidadeFornecedor"));

        Fornecedores fornecedor = new Fornecedores(Integer.parseInt(idFornecedor), nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);

        FornecedoresDAO.atualizar(fornecedor);
        //RequestDispatcher dispatcher = request.getRequestDispatcher("Produto.jsp");
        //dispatcher.forward(request, response);
        response.sendRedirect("visualizarFornecedor");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        int id = Integer.parseInt(request.getParameter("idFornecedor"));
        request.setAttribute("pagina", "ATUALIZAR FORNECEDOR");
        try {
            ArrayList<Fornecedores> fornecedor = FornecedoresDAO.encontrarFornecedorPorId(id);
            request.setAttribute("nome", fornecedor.get(0).getNome());
            request.setAttribute("cnpj", fornecedor.get(0).getCnpj());
            request.setAttribute("telefone", fornecedor.get(0).getTelefone());
            request.setAttribute("endereco", fornecedor.get(0).getEndereco());
            request.setAttribute("numero", fornecedor.get(0).getNumero());
            request.setAttribute("complemento", fornecedor.get(0).getComplemento());
            request.setAttribute("cep", fornecedor.get(0).getCep());
            request.setAttribute("bairro", fornecedor.get(0).getBairro());
            request.setAttribute("cidade", fornecedor.get(0).getCidade());
            request.setAttribute("estadoFornecedor", fornecedor.get(0).getEstado());
            request.setAttribute("disponibilidadeFornecedor", fornecedor.get(0).getDisponibilidade());
            request.setAttribute("idFornecedor", id);
            request.setAttribute("action", "editarFornecedor");
            ArrayList<Estado> listaEstados = EstadoDAO.listarEstados();
            for (int i = 0; i < listaEstados.size(); i++) {
                if (listaEstados.get(i).getNome().equals(fornecedor.get(0).getEstado())) {
                    listaEstados.remove(i);
                }
            }
            request.setAttribute("listarEstados", listaEstados);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("CadastroFornecedores.jsp").forward(request, response);

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
