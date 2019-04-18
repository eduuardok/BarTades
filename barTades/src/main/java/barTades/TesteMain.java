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
import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Fornecedores;
import com.bartades.model.Usuario;

/**
 *
 * @author ELuna
 */
public class TesteMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		//ArrayList<Usuario> usuario = UsuarioDAO.encontrarUsuarioPorId(9);

		//for (Usuario u : usuario) {
		//	System.out.println(u.getNome());
		//}
		
		ArrayList<Fornecedores> listaFornecedores = FornecedoresDAO.listar();
		
		for(int i = 0; i < listaFornecedores.size(); i++) {
			//System.out.println(f.getNome());
			if(listaFornecedores.get(i).getNome().equals("FornecedorTesteDois")) {
			System.out.println(listaFornecedores.get(i).getNome());
			listaFornecedores.remove(i);
		}
		
		}
		
		for(Fornecedores f : listaFornecedores) {
			System.out.println("Novo array");
			System.out.println(f.getNome());
		}
		
	}

}
