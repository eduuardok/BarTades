/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barTades;

import java.awt.List;
import java.sql.SQLException;
import java.util.ArrayList;
import com.bartades.dao.FornecedoresDAO;
import com.bartades.dao.ProdutoDAO;
import com.bartades.dao.UsuarioDAO;
import com.bartades.dao.PedidoDAO;
import com.bartades.model.Fornecedores;
import com.bartades.model.PedidoCompraProduto;
import com.bartades.model.Produto;
import com.bartades.model.Pedido;
import com.bartades.model.Usuario;
import com.bartades.model.Categoria;
import com.bartades.dao.CategoriaDAO;
import com.bartades.dao.CompraProdutoDAO;
import java.util.LinkedList;

/**
 *
 * @author ELuna
 */
public class TesteMain {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        ArrayList<Produto> listaProdutos = ProdutoDAO.listarProdutos();
        Pedido f = new Pedido("Cartão", 1, "Vitão");
        
        for (Produto p: listaProdutos) {
            
            f.adicionarProduto(p);
            
        }

        PedidoDAO.SalvarPedido(f);
        
    }

}
