package com.bartades.dao;

import com.bartades.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio Carlos
 */
public class FornecedoresDAO {

    public static boolean incluir(Fornecedores fornecedor) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "INSERT INTO fornecedores (nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, true)";
        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getNumero());
            stmt.setString(6, fornecedor.getComplemento());
            stmt.setString(7, fornecedor.getCep());
            stmt.setString(8, fornecedor.getBairro());
            stmt.setString(9, fornecedor.getCidade());
            stmt.setString(10, fornecedor.getEstado());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        }
        return retorno;
    }

    public static boolean atualizar(Fornecedores fornecedor) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "UPDATE fornecedores SET nome=?,"
                + "cnpj=?, telefone=?, endereco=?, numero=?, complemento=?, cep=?,bairro=?,cidade=?,estado=? "
                + "WHERE id=?";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {

            stmt.setString(1, fornecedor.getNome());
            stmt.setString(2, fornecedor.getCnpj());
            stmt.setString(3, fornecedor.getTelefone());
            stmt.setString(4, fornecedor.getEndereco());
            stmt.setString(5, fornecedor.getNumero());
            stmt.setString(6, fornecedor.getComplemento());
            stmt.setString(7, fornecedor.getCep());
            stmt.setString(8, fornecedor.getBairro());
            stmt.setString(9, fornecedor.getCidade());
            stmt.setString(10, fornecedor.getEstado());
            stmt.setInt(11, fornecedor.getID());

            stmt.executeUpdate();

        }
        return retorno;
    }

    public Fornecedores obterFornecedores(String idFornecedor) throws ClassNotFoundException, SQLException {

        Fornecedores f = new Fornecedores();

        String sql = "SELECT * FROM fornecedores where cnpj = ? AND enabled = true";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, idFornecedor);
            ResultSet resultados = stmt.executeQuery();

            while (resultados.next()) {
                f.setID(resultados.getInt("id"));
                f.setNome(resultados.getString("nome"));
                f.setCnpj(resultados.getString("cnpj"));
                f.setTelefone(resultados.getString("telefone"));
                f.setEndereco(resultados.getString("endereco"));
                f.setNumero(resultados.getString("numero"));
                f.setComplemento(resultados.getString("complemento"));
                f.setCep(resultados.getString("cep"));
                f.setBairro(resultados.getString("bairro"));
                f.setCidade(resultados.getString("cidade"));
                f.setEstado(resultados.getString("estado"));

            }
        }
        return f;
    }

    public List<Fornecedores> listar() throws ClassNotFoundException, SQLException {

        String sql = "SELECT id, nome,cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado"
                + "FROM bartades WHERE enabled = true";

        List<Fornecedores> lista = new ArrayList<Fornecedores>();

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet resultados = stmt.executeQuery(sql);

            while (resultados.next()) {
                int id = resultados.getInt("id");
                String nome = resultados.getString("nome");
                String cnpj = resultados.getString("cnpj");
                String telefone = resultados.getString("telefone");
                String endereco = resultados.getString("endereco");
                String numero = resultados.getString("numero");
                String complemento = resultados.getString("complemento");
                String cep = resultados.getString("cep");
                String bairro = resultados.getString("bairro");
                String cidade = resultados.getString("cidade");
                String estado = resultados.getString("Estado");

                Fornecedores fornecedor = new Fornecedores(id, nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado);
                lista.add(fornecedor);
            }

        }
        return lista;
    }

    public void remover(int id) throws ClassNotFoundException, SQLException {

        String sql = "UPDATE fornecedores SET ENABLED=false WHERE id= ?";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        }
    }

}
