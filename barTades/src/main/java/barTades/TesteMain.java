/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barTades;

import com.bartades.controller.ProdutoController;
import com.bartades.dao.ProdutoDAO;
import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Produto;
import com.bartades.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELuna
 */
public class TesteMain {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        ArrayList<Produto> produtos = ProdutoController.listarProdutos();
        
        for(int i = 0; i < produtos.size(); i++){
            System.out.println(produtos.get(i).getNome());
        }

}
    
    
    
}
