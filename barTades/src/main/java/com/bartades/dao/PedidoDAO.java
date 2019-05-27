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

        String sql = "insert into pedidos(cliente_nome, unidade_venda, forma_pagamento) values (?,?,?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);) {
            insert.setString(1, p.getNomeCliente());
            insert.setInt(2, p.getUnidade());
            insert.setString(3, p.getTipoPagamento());

            int linhasAfetadas = insert.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
            conn.close();
        }

        idPedido = getUltimoID();

        if (idPedido >= 0) {

            for (Produto produto : p.getProdutos()) {

                sql = "insert into produtos_pedidos(id_pedido,id_produto,quantidade) values (?,?,?);";

                try (Connection conn = InterfaceConexao.obterConexao();
                        PreparedStatement insert = conn.prepareStatement(sql);) {

                    insert.setInt(1, idPedido);
                    insert.setInt(2, produto.getId());
                    insert.setInt(3, produto.getQuantidade());

                    int linhasAfetadas = insert.executeUpdate();

                    if (linhasAfetadas > 0 && retorno) {
                        retorno = true;
                    } else {
                        retorno = false;
                    }
                    conn.close();

                }

            }
        }

        return retorno;
    }

    public static int getUltimoID() throws ClassNotFoundException, SQLException {

        int id = -1;

        String sql = "select max(p.id) from pedidos p group by id";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                id = retorno.getInt("id");
            }
        }

        return id;
    }

    /**
     * Este método atualiza um pedido já existente no banco de dados
     *
     * @param p = Objeto do tipo Pedido
     * @return boolean: true = sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean AtualizarPedido(Produto p) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "UPDATE pedidos set nome = ?, descricao = ?, categoria = ?, preco_venda = ?, preco_compra = ?, id_fornecedor = ?, disponibilidade = ?, id_franquia = ? WHERE id = ?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement update = conn.prepareStatement(sql);) {
            update.setString(1, p.getNome());
            update.setString(2, p.getDescricao());
            update.setInt(3, encontrarIdCategoria(p.getCategoria()));
            update.setDouble(4, p.getPrecoVenda());
            update.setDouble(5, p.getPrecoCompra());
            update.setInt(6, encontrarIdFornecedor(p.getFornecedor()));

            update.setBoolean(7, p.getDisponibilidade());
            update.setInt(8, encontrarIdUnidade(p.getUnidade()));
            update.setInt(9, p.getId());

            int linhasAfetadas = update.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
            conn.close();
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
