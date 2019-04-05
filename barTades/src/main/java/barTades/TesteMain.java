/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barTades;

import com.bartades.controller.ProdutoController;
import com.bartades.model.Produto;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author ELuna
 */
public class TesteMain {
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        ArrayList<Produto> teste = ProdutoController.listarProdutos();
        
        for(Produto p : teste){
            System.out.println(p.getNome());
        }
        
        Produto p1 = teste.get(0);
        
        System.out.println(p1.getNome());
        
        ProdutoController.SalvarProduto("ProdutoDois", "Teste dois", "CategoriaTesteDois", 400.0, 30.0, "FornecedorTesteDois");
        
    }
    
    
    
}
