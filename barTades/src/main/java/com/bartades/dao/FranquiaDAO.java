package com.bartades.dao;

import com.bartades.model.Franquia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FranquiaDAO {

    public static ArrayList<Franquia> listarFranquias() throws ClassNotFoundException, SQLException {

        ArrayList<Franquia> listaFranquias = new ArrayList();

        String sql = "select unidades.id, unidades.nome, unidades.endereco, unidades.numero, unidades.complemento, unidades.cep, unidades.bairro, unidades.cidade, unidades.enabled, estado.nome  as estado from unidades inner join estado on unidades.estado = estado.id;";

        try (Connection conn = com.bartades.dao.InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()) {

            while (retorno.next()) {
                Franquia f = new Franquia(
                        retorno.getInt("id"),
                        retorno.getString("nome"),
                        retorno.getString("endereco"),
                        retorno.getString("numero"),
                        retorno.getString("complemento"),
                        retorno.getString("cep"),
                        retorno.getString("bairro"),
                        retorno.getString("cidade"),
                        retorno.getString("estado"),
                        retorno.getBoolean("ENABLED"));
                listaFranquias.add(f);
            }
        }
        return listaFranquias;
    }

    public static boolean SalvarFranquia(Franquia f) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "INSERT INTO unidades (nome, endereco, numero, complemento, cep, bairro, cidade, estado, enabled) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);) {
            insert.setString(1, f.getNome());
            insert.setString(2, f.getEndereco());
            insert.setString(3, f.getNumero());
            insert.setString(4, f.getComplemento());
            insert.setString(5, f.getCep());
            insert.setString(6, f.getBairro());
            insert.setString(7, f.getCidade());
            insert.setInt(8, encontrarIdEstado(f.getEstado()));
            insert.setBoolean(9, f.isDisponibilidade());

            int linhasAfetadas = insert.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
        }
        return retorno;
    }

    public static boolean AtualizarFranquia(Franquia f) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "UPDATE unidades set nome = ?, endereco=?, numero=?, complemento=?, cep=?, bairro=?, cidade=?, estado=?, enabled=? WHERE id = ?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement update = conn.prepareStatement(sql);) {
            update.setString(1, f.getNome());
            update.setString(2, f.getEndereco());
            update.setString(3, f.getNumero());
            update.setString(4, f.getComplemento());
            update.setString(5, f.getCep());
            update.setString(6, f.getBairro());
            update.setString(7, f.getCidade());
            update.setInt(8, encontrarIdEstado(f.getEstado()));
            update.setBoolean(9, f.isDisponibilidade());
            update.setInt(10, f.getId());

            int linhasAfetadas = update.executeUpdate();

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

    public static ArrayList<Franquia> encontrarFranquiaPorId(int id) throws ClassNotFoundException, SQLException {

        ArrayList<Franquia> franquiaRetorno = new ArrayList<Franquia>();

        String sql = "select unidades.id, unidades.nome, unidades.endereco, unidades.numero, unidades.complemento, unidades.cep, unidades.bairro, unidades.cidade, unidades.enabled, estado.nome  as estado from unidades inner join estado on unidades.estado = estado.id where unidades.id=?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setInt(1, id);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                Franquia f = new Franquia(
                        retorno.getInt("id"),
                        retorno.getString("nome"),
                        retorno.getString("endereco"),
                        retorno.getString("numero"),
                        retorno.getString("complemento"),
                        retorno.getString("cep"),
                        retorno.getString("bairro"),
                        retorno.getString("cidade"),
                        retorno.getString("estado"),
                        retorno.getBoolean("enabled"));
                franquiaRetorno.add(f);
            }

        }
        return franquiaRetorno;
    }

}
