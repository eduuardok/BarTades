package com.bartades.services;

import java.sql.SQLException;

import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class loginService {
	
	public static boolean realizarLogin(String email, String senha) throws ClassNotFoundException, SQLException {
		
		Usuario user = UsuarioDAO.encontrarUsuarioPorEmail(email);
		
		if(user.getEmail() != null && user.getSenha().equals(senha)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public static boolean isUsuarioLogado(HttpServletRequest request, HttpServletResponse response, HttpSession sessao) {
		
		if(sessao.getAttribute("usuario") != null) {
			return true;
		} else {
			return false;
		}
		
	}
}
