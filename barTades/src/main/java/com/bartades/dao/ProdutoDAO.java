/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bartades.dao;

import com.bartades.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELuna
 */
public class ProdutoDAO {

    public static ArrayList<Produto> encontrarProdutoPorId(int id) throws ClassNotFoundException, SQLException {

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
                + "where p.id = ?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {
            select.setInt(1, id);
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
     * Este método lista todos os produtos que estão no banco de dados, já com
     * fornecedor e categoria em String
     *
     * @return ArrayList do tipo Produto
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ArrayList<Produto> listarProdutos() throws ClassNotFoundException, SQLException {

        ArrayList<Produto> listaProdutos = new ArrayList();

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
                + "join unidades u on p.id_franquia = u.id ";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()) {

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
                listaProdutos.add(p);
            }
            conn.close();
        }

        return listaProdutos;
    }

    /**
     * Este método salva um novo produto no banco de dados
     *
     * @param p = Objeto do tipo Produto
     * @return boolean: true = sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean SalvarProduto(Produto p) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "INSERT INTO produtos (nome, descricao, categoria, preco_venda, preco_compra, id_fornecedor, disponibilidade, quantidade_disponivel, id_franquia) values (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);) {
            insert.setString(1, p.getNome());
            insert.setString(2, p.getDescricao());
            insert.setInt(3, encontrarIdCategoria(p.getCategoria()));
            insert.setDouble(4, p.getPrecoVenda());
            insert.setDouble(5, p.getPrecoCompra());
            insert.setInt(6, encontrarIdFornecedor(p.getFornecedor()));
            insert.setBoolean(7, p.getDisponibilidade());
            insert.setInt(8, 0);
            insert.setInt(9, encontrarIdUnidade(p.getUnidade()));
            

            int linhasAfetadas = insert.executeUpdate();

            if (linhasAfetadas > 0) {
                retorno = true;
            }
            conn.close();
        }
        return retorno;
    }

    /**
     * Este método atualiza um produto já existente no banco de dados
     *
     * @param p = Objeto do tipo Produto
     * @return boolean: true = sucesso, false = falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean AtualizarProduto(Produto p) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "UPDATE produtos set nome = ?, descricao = ?, categoria = ?, preco_venda = ?, preco_compra = ?, id_fornecedor = ?, disponibilidade = ?, id_franquia = ? WHERE id = ?;";

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
