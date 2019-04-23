package com.bartades.services;

import java.sql.SQLException;

import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Usuario;

public class loginService {
	
	public static boolean realizarLogin(String email, String senha) throws ClassNotFoundException, SQLException {
		
		Usuario user = UsuarioDAO.encontrarUsuarioPorEmail(email);
		
		if(user.getEmail() != null && user.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
		
	}

}
