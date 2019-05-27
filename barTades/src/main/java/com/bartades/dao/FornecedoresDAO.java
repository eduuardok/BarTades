package com.bartades.dao;

import com.bartades.model.Fornecedores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Antonio Carlos
 */
public class FornecedoresDAO {

    public static boolean incluir(Fornecedores fornecedor) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "INSERT INTO fornecedores (nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
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
            stmt.setInt(10, encontrarIdEstado(fornecedor.getEstado()));
            stmt.setBoolean(11, fornecedor.getDisponibilidade());
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }

        }
        return retorno;
    }

    private static int encontrarIdEstado(String nomeEstado) throws ClassNotFoundException, SQLException {

        String sql = "SELECT id FROM estado WHERE nome = ?";

        int idEstado = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setString(1, nomeEstado);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                idEstado = retorno.getInt("id");
            }

        }

        return idEstado;
    }

    public static boolean atualizar(Fornecedores fornecedor) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "UPDATE fornecedores SET nome=?, cnpj=?, telefone=?, endereco=?, numero=?, complemento=?, cep=?, bairro=?, cidade=?, estado=?, enabled=?"
                + " WHERE ID=?";

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
            stmt.setInt(10, encontrarIdEstado(fornecedor.getEstado()));
            stmt.setBoolean(11, fornecedor.getDisponibilidade());
            stmt.setInt(12, fornecedor.getID());

            stmt.executeUpdate();

        }
        return retorno;
    }

    public static ArrayList<Fornecedores> listar() throws ClassNotFoundException, SQLException {

        String sql = "SELECT id, nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, ENABLED \n"
                + "FROM fornecedores where ENABLED = true";

        ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

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
                boolean disponibilidade = resultados.getBoolean("ENABLED");

                Fornecedores fornecedor = new Fornecedores(id, nome, cnpj, telefone, endereco, numero, complemento, cep, bairro, cidade, estado, disponibilidade);
                lista.add(fornecedor);
            }

        }
        return lista;
    }

    public static ArrayList<Fornecedores> listarFornecedores() throws ClassNotFoundException, SQLException {

        ArrayList<Fornecedores> listarFornecedores = new ArrayList();

        String sql = "select fornecedores.id, fornecedores.nome, fornecedores.cnpj, fornecedores.telefone, fornecedores.endereco, fornecedores.numero, fornecedores.complemento, fornecedores.cep, fornecedores.bairro, fornecedores.cidade, fornecedores.enabled, estado.nome  as estado from fornecedores inner join estado on fornecedores.estado = estado.id;\n"
                + "\n";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()) {

            while (retorno.next()) {
                Fornecedores f = new Fornecedores(
                        retorno.getInt("ID"),
                        retorno.getString("nome"),
                        retorno.getString("cnpj"),
                        retorno.getString("telefone"),
                        retorno.getString("endereco"),
                        retorno.getString("numero"),
                        retorno.getString("complemento"),
                        retorno.getString("cep"),
                        retorno.getString("bairro"),
                        retorno.getString("cidade"),
                        retorno.getString("estado"),
                        retorno.getBoolean("enabled"));
                listarFornecedores.add(f);
            }

        }

        return listarFornecedores;
    }
    
    public static ArrayList<Fornecedores> encontrarIdPorNome(String nome) throws ClassNotFoundException, SQLException {

        ArrayList<Fornecedores> fornecedorRetorno = new ArrayList<Fornecedores>();

        String sql = "select fornecedores.id, fornecedores.nome, fornecedores.cnpj, fornecedores.telefone, fornecedores.endereco, fornecedores.numero, fornecedores.complemento, fornecedores.cep, fornecedores.bairro, fornecedores.cidade, fornecedores.enabled, estado.nome  as estado from fornecedores inner join estado on fornecedores.estado = estado.id where fornecedores.nome=?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setString(1, nome);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                Fornecedores f = new Fornecedores(
                        retorno.getInt("ID"),
                        retorno.getString("nome"),
                        retorno.getString("cnpj"),
                        retorno.getString("telefone"),
                        retorno.getString("endereco"),
                        retorno.getString("numero"),
                        retorno.getString("complemento"),
                        retorno.getString("cep"),
                        retorno.getString("bairro"),
                        retorno.getString("cidade"),
                        retorno.getString("estado"),
                        retorno.getBoolean("enabled"));
                fornecedorRetorno.add(f);
            }

        }
        return fornecedorRetorno;
    }
    
    public static ArrayList<Fornecedores> encontrarFornecedorPorId(int id) throws ClassNotFoundException, SQLException {

        ArrayList<Fornecedores> fornecedorRetorno = new ArrayList<Fornecedores>();

        String sql = "select fornecedores.id, fornecedores.nome, fornecedores.cnpj, fornecedores.telefone, fornecedores.endereco, fornecedores.numero, fornecedores.complemento, fornecedores.cep, fornecedores.bairro, fornecedores.cidade, fornecedores.enabled, estado.nome  as estado from fornecedores inner join estado on fornecedores.estado = estado.id where fornecedores.id=?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setInt(1, id);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                Fornecedores f = new Fornecedores(
                        retorno.getInt("ID"),
                        retorno.getString("nome"),
                        retorno.getString("cnpj"),
                        retorno.getString("telefone"),
                        retorno.getString("endereco"),
                        retorno.getString("numero"),
                        retorno.getString("complemento"),
                        retorno.getString("cep"),
                        retorno.getString("bairro"),
                        retorno.getString("cidade"),
                        retorno.getString("estado"),
                        retorno.getBoolean("enabled"));
                fornecedorRetorno.add(f);
            }

        }
        return fornecedorRetorno;
    }

}
