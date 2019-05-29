package com.bartades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bartades.model.PedidoCompraProduto;
import com.bartades.model.Produto;
import com.mysql.cj.x.protobuf.MysqlxConnection.Close;

public class CompraProdutoDAO {

    /**
     * Este método salva uma venda no banco de dados e retorna o código do
     * pedido
     *
     * @param p = Objeto do tipo PedidoCompraProduto com quantidade de produtos
     * e valor total do pedido
     * @return int = codigo do pedido no banco de dados, utilizado para inserir
     * o detalhe do pedido
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static int SalvarPedido(PedidoCompraProduto p) throws ClassNotFoundException, SQLException {

        int lastId = 0;
        String sql = "INSERT INTO pedidos_compra (qtde_produtos, valor_total_compra, data_pedido, usuario_pedido) values (?, ?, now(), ?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            insert.setInt(1, p.getQuantidadeProdutos());
            insert.setDouble(2, p.getValorTotalPedido());
            insert.setInt(3, p.getIdUsuarioPedido());

            insert.executeUpdate();

            ResultSet rs = insert.getGeneratedKeys();

            if (rs.next()) {
                lastId = rs.getInt(1);
            }
            conn.close();
        }
        return lastId;
    }

    /**
     * Método utilizado para salvar o detalhe (produtos) de cada pedido no banco
     * de dados
     *
     * ***Este método utiliza um método privado para somar a quantidade
     * comprada na tabela de produtos do banco de dados
     * {@link #SalvarQuantidadeProdutos()}
     *
     * @param p1 = Objeto do tipo PedidoCompraProduto completo(codigo, lista de
     * produtos e valor total)
     *
     * @return boolean = true se sucesso, false se falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static boolean SalvarDetalhe(PedidoCompraProduto p1) throws ClassNotFoundException, SQLException {

        boolean retorno = false;

        String sql = "INSERT INTO produtos_pedidos_compra (id_pedido, id_produto, quantidade, valor_total) values (?, ?, ?, ?);";

        int linhasAfetadas = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);) {

            for (Produto p : p1.getProdutos()) {

                insert.setInt(1, p1.getCodigoPedido());
                insert.setInt(2, p.getId());
                insert.setInt(3, p.getQuantidade());
                insert.setDouble(4, p.getValorTotalCompra());

                linhasAfetadas = insert.executeUpdate();

            }

            if (linhasAfetadas > 0) {
                retorno = true;
            }
            conn.close();
        }

        boolean retornoEstoque = SalvarQuantidadeProdutos(p1);

        if (retorno && retornoEstoque) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método auxiliar utilizado para somar a quantidade de produtos comprados
     * no estoque. É utilizado no método
     * {@link #SalvarDetalhe(PedidoCompraProduto)}
     *
     * @param p1 = Objeto do tipo PedidoCompraProduto completo, mesmo utilizado
     * no método principal
     * @return = boolean = true se sucesso, false se falha
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static boolean SalvarQuantidadeProdutos(PedidoCompraProduto p1) throws ClassNotFoundException, SQLException {

        boolean retorno = false;
        ArrayList<Produto> listaGeral = ProdutoDAO.listarProdutos();
        ArrayList<Produto> listaFinalCompra = new ArrayList<Produto>();

        for (Produto geral : listaGeral) {
            for (Produto p2 : p1.getProdutos()) {
                if (geral.getId() == p2.getId()) {
                    geral.adicionarQuantidade(p2.getQuantidade());
                    listaFinalCompra.add(geral);
                }
            }
        }

        String sql = "UPDATE produtos set quantidade_disponivel = ? where id = ?;";

        int linhasAfetadas = 0;

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);) {

            for (Produto p : listaFinalCompra) {

                insert.setInt(1, p.getQuantidade());
                insert.setInt(2, p.getId());

                linhasAfetadas = insert.executeUpdate();

            }

            if (linhasAfetadas > 0) {
                retorno = true;
            }
            conn.close();
        }

        return retorno;
    }

    /**
     * Este método lista todos os pedidos de compra cadastrados no bd
     *
     * @return ArrayList do tipo PedidoCompraProduto
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ArrayList<PedidoCompraProduto> listarPedidosCompra() throws ClassNotFoundException, SQLException {

        ArrayList<PedidoCompraProduto> listaPedidos = new ArrayList<PedidoCompraProduto>();
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

        String sql = "select pc.id, pc.qtde_produtos, pc.valor_total_compra, date_format(pc.data_pedido, '%d-%m-%Y') AS data_pedido, u.nome as nome_usuario from pedidos_compra pc join usuarios u where pc.usuario_pedido = u.id;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);
                ResultSet retorno = select.executeQuery()) {

            while (retorno.next()) {
                PedidoCompraProduto p = new PedidoCompraProduto(
                        retorno.getInt("id"),
                        retorno.getInt("qtde_produtos"),
                        listaProdutos = produtosPedidoCompra(retorno.getInt("id")),
                        retorno.getDouble("valor_total_compra"),
                        retorno.getString("data_pedido"),
                        retorno.getString("nome_usuario"));
                listaPedidos.add(p);
            }
            conn.close();
        }

        return listaPedidos;
    }

    /**
     * Este método encontra os produtos de um determinado pedido
     *
     * @param CODIGOPEDIDO = int - codigo do pedido
     * @return - ArrayList do tipo Produto
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private static ArrayList<Produto> produtosPedidoCompra(int CODIGOPEDIDO) throws ClassNotFoundException, SQLException {

        ArrayList<Produto> listaProdutosPedidos = new ArrayList<Produto>();

        String sql = "select id_pedido, id_produto, quantidade, valor_total from produtos_pedidos_compra where id_pedido = ?";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {

            select.setInt(1, CODIGOPEDIDO);

            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                Produto p = new Produto(
                        retorno.getInt("id_produto"),
                        retorno.getInt("quantidade"),
                        retorno.getDouble("valor_total"));
                listaProdutosPedidos.add(p);
            }
            conn.close();
        }
        return listaProdutosPedidos;
    }

    /**
     * Este método encontra um pedido pelo codigo
     *
     * @param idPedido = int - codigo do pedido
     * @return ArrayList do tipo PedidoCompraProduto com 1 elemento
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static ArrayList<PedidoCompraProduto> EncontrarPedidoCompraPorId(int idPedido) throws ClassNotFoundException, SQLException {

        ArrayList<PedidoCompraProduto> listaPedidos = new ArrayList<PedidoCompraProduto>();
        ArrayList<Produto> listaProdutos = new ArrayList<Produto>();

        String sql = "select pc.id, pc.qtde_produtos, pc.valor_total_compra, date_format(pc.data_pedido, '%d-%m-%Y') AS data_pedido, u.nome as nome_usuario from pedidos_compra pc join usuarios u on pc.usuario_pedido = u.id where pc.id = ?;";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement select = conn.prepareStatement(sql);) {

            select.setInt(1, idPedido);

            ResultSet retorno = select.executeQuery();

            while (retorno.next()) {
                PedidoCompraProduto p = new PedidoCompraProduto(
                        retorno.getInt("id"),
                        retorno.getInt("qtde_produtos"),
                        listaProdutos = produtosPedidoCompra(retorno.getInt("id")),
                        retorno.getDouble("valor_total_compra"),
                        retorno.getString("data_pedido"),
                        retorno.getString("nome_usuario"));
                listaPedidos.add(p);
            }
            conn.close();
        }

        return listaPedidos;
    }

}
