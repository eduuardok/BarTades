/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barTades;

import com.bartades.controller.CategoriaController;
import com.bartades.controller.FranquiaController;
import com.bartades.controller.ProdutoController;
import com.bartades.dao.ProdutoDAO;
import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Categoria;
import com.bartades.model.Franquia;
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
    
    	/*
    	FranquiaController.SalvarFranquia("UnidadeTeste3", "BA", "Avenida dos perneta, 600");
        
    	//FranquiaController.AtualizarFranquia(1, "UnidadeTeste1", "PB", "Avenida curralinho, 80");

        ArrayList<Franquia> franquias = FranquiaController.listarFranquias();
        
        for(Franquia f : franquias) {
        	System.out.println(f.getNome());
        	System.out.println(f.getEndereco());
        }
        
        FranquiaController.SalvarFranquia("UnidadeTeste3", "BA", "Avenida dos perneta, 600");
      */  
        ArrayList<Categoria> categorias = CategoriaController.listarCategorias();
        
        for(Categoria c : categorias) {
        	System.out.println(c.getNome());
        }
}
    
    
    
}
