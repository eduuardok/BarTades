/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.dao;

import com.bartades.model.Produto;
import com.bartades.model.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static javafx.beans.binding.Bindings.select;

/**
 *
 * @author ELuna
 */
public class PedidoDAO {

    /**
     * Este método salva um novo pedido no banco de dados
     *
     * @param p = Objeto do tipo Pedido
     * @return boolean: true = sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean SalvarPedido(Pedido p) throws ClassNotFoundException, SQLException {

        boolean retorno = false;
        int idPedido = -1;

        String sql = "insert into pedidos(cliente_nome, unidade_venda, tipo_pagamento) values (?,?,?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            insert.setString(1, p.getNomeCliente());
            insert.setInt(2, p.getUnidade());
            insert.setString(3, p.getTipoPagamento());

            insert.executeUpdate();

            ResultSet rs = insert.getGeneratedKeys();

            if (rs.next()) {
                idPedido = rs.getInt(1);
            }

            if (idPedido > 0) {
                retorno = true;
            }
            conn.close();
        }

        if (idPedido >= 0 && retorno) {

            for (Produto produto : p.getProdutos()) {

                sql = "insert into produtos_pedidos(id_pedido,id_produto,quantidade) values (?,?,?);";

                try (Connection conn = InterfaceConexao.obterConexao();
                        PreparedStatement insert = conn.prepareStatement(sql);) {

                    insert.setInt(1, idPedido);
                    insert.setInt(2, produto.getId());
                    insert.setInt(3, produto.getQuantidade());

                    int linhasAfetadas = insert.executeUpdate();

                    retorno = (linhasAfetadas > 0 && retorno);

                    conn.close();

                }

                sql = "update produtos set quantidade_disponivel = quantidade_disponivel - ? where id = ?;";

                try (Connection conn = InterfaceConexao.obterConexao();
                        PreparedStatement update = conn.prepareStatement(sql);) {

                    update.setInt(1, produto.getQuantidade());
                    update.setInt(2, produto.getId());

                    int linhasAfetadas = update.executeUpdate();

                    retorno = (linhasAfetadas > 0 && retorno);

                    conn.close();

                }
            }
        }
        return retorno;
    }

    /**
     * Este método atualiza um pedido já existente no banco de dados
     *
     * @param p = Objeto do tipo Pedido
     * @return boolean: true = sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean deletarProduto(Pedido p, int idProduto) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "DELETE FROM produtos_pedidos where id_pedido = ? and id_produtos = ?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement delete = conn.prepareStatement(sql);) {

            delete.setInt(1, p.getID());
            delete.setInt(2, idProduto);

            int linhasAfetadas = delete.executeUpdate();

            retorno = linhasAfetadas > 0;

        }

        return retorno;

    }


    /**
     * (Este é um método auxiliar) Utilizado para encontrar o id da categoria no
     * banco de dados
     *
     * @param nomeCategoria = String nome da categoria
     * @return int = id da categoria
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static int encontrarIdCategoria(String nomeCategoria) throws ClassNotFoundException, SQLException {

        String sql = "SELECT id FROM categoria WHERE nome = ?";

        int idCategoria = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setString(1, nomeCategoria);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                idCategoria = retorno.getInt("id");
            }
            conn.close();
        }

        return idCategoria;
    }

    /**
     * Este método retorna todos os produtos de uma categoria
     *
     * @param categoria = String nome da categoria
     * @return ArrayList do tipo Produto
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ArrayList<Produto> encontrarProdutoPorCategoria(int categoria) throws ClassNotFoundException, SQLException {

        ArrayList<Produto> produtoRetorno = new ArrayList<Produto>();

        String sql = "select p.id,\n"
                + "p.nome, \n"
                + "p.descricao, \n"
                + "c.nome as categoria, \n"
                + "p.preco_venda, \n"
                + "p.preco_compra, \n"
                + "f.nome as fornecedor, \n"
                + "p.quantidade_disponivel, \n"
                + "p.disponibilidade, \n"
                + "u.nome as unidade \n"
                + "from produtos p \n"
                + "join categoria c on p.categoria = c.id \n"
                + "join fornecedores f on p.id_fornecedor = f.id \n"
                + "join unidades u on p.id_franquia = u.id \n"
                + "where p.categoria = ? and p.disponibilidade = 1;";

        System.out.println(sql);

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setInt(1, categoria);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                Produto p = new Produto(
                        retorno.getInt("ID"),
                        retorno.getString("nome"),
                        retorno.getString("descricao"),
                        retorno.getString("categoria"),
                        retorno.getDouble("preco_venda"),
                        retorno.getDouble("preco_compra"),
                        retorno.getString("fornecedor"),
                        retorno.getInt("quantidade_disponivel"),
                        retorno.getBoolean("disponibilidade"),
                        retorno.getString("unidade"));
                produtoRetorno.add(p);
            }
            conn.close();
        }
        return produtoRetorno;
    }

    /**
     * (Este é um método auxilar) Utilizado para encontrar o id do fornecedor no
     * banco de dados
     *
     * @param nomeFornecedor = String nome do fornecedor
     * @return int = id do fornecedor
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static int encontrarIdFornecedor(String nomeFornecedor) throws ClassNotFoundException, SQLException {

        String sql = "SELECT id FROM fornecedores WHERE nome = ?";

        int idFornecedor = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setString(1, nomeFornecedor);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                idFornecedor = retorno.getInt("id");
            }
            conn.close();
        }

        return idFornecedor;
    }

    /**
     * Método auxiliar para encontrar o id da unidade no banco de dados
     *
     * @param nomeUnidade = String nome da unidade
     * @return id = id da unidade cadastrada no banco
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static int encontrarIdUnidade(String nomeUnidade) throws ClassNotFoundException, SQLException {

        String sql = "SELECT id FROM unidades WHERE nome = ?";

        int idUnidade = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setString(1, nomeUnidade);
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                idUnidade = retorno.getInt("id");
            }
            conn.close();
        }

        return idUnidade;

    }

}
