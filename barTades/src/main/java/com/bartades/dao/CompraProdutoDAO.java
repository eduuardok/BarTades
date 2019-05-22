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

	public static int SalvarPedido(PedidoCompraProduto p) throws ClassNotFoundException, SQLException {

        int lastId = 0;
        String sql = "INSERT INTO pedidos_compra (qtde_produtos, valor_total_compra) values (?, ?);";

        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        		) {
        	
            insert.setInt(1, p.getQuantidadeProdutos());
            insert.setDouble(2, p.getValorTotalPedido());
            
            insert.executeUpdate();
            
            
            ResultSet rs = insert.getGeneratedKeys();
            
            if(rs.next()) {
            	lastId = rs.getInt(1);
            }
            conn.close();
        }
        return lastId;
    }
	
	public static boolean SalvarDetalhe(PedidoCompraProduto p1) throws ClassNotFoundException, SQLException {
		
		boolean retorno = false;
		
		String sql = "INSERT INTO produtos_pedidos_compra (id_pedido, id_produto, quantidade, valor_total) values (?, ?, ?, ?);";

		int linhasAfetadas = 0;
		
        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);
        		) {
        	
        	for(Produto p : p1.getProdutos()) {
        		
            insert.setInt(1, p1.getCodigoPedido());
            insert.setInt(2, p.getId());
            insert.setInt(3, p.getQuantidade());
            insert.setDouble(4, p.getValorTotalCompra());
            
            linhasAfetadas = insert.executeUpdate();
            
        	}
           
        	if(linhasAfetadas > 0) {
        		retorno = true;
        	}
        	conn.close();
        }
        
        boolean retornoEstoque = SalvarQuantidadeProdutos(p1);
        
        if(retorno && retornoEstoque) {
        	return true;
        } else {
        	return false;
        }
	}
	
	private static boolean SalvarQuantidadeProdutos(PedidoCompraProduto p1) throws ClassNotFoundException, SQLException {
		
		boolean retorno = false;
		ArrayList<Produto> listaGeral = ProdutoDAO.listarProdutos();
		ArrayList<Produto> listaFinalCompra = new ArrayList<Produto>();
		
		for(Produto geral : listaGeral) {
			for(Produto p2 : p1.getProdutos()) {
				if(geral.getId() == p2.getId()) {
					geral.adicionarQuantidade(p2.getQuantidade());
					listaFinalCompra.add(geral);
				}
			}
		}
		
		String sql = "UPDATE produtos set quantidade_disponivel = ? where id = ?";

		int linhasAfetadas = 0;
		
        try (Connection conn = InterfaceConexao.obterConexao();
                PreparedStatement insert = conn.prepareStatement(sql);
        		) {
        	
        	for(Produto p : listaFinalCompra) {
        		
            insert.setInt(1, p.getQuantidade());
            insert.setInt(2, p.getId());
           
            linhasAfetadas = insert.executeUpdate();
            
        	}
           
        	if(linhasAfetadas > 0) {
        		retorno = true;
        	}
        	conn.close();
        }
		
		return retorno;
	}
	
}
