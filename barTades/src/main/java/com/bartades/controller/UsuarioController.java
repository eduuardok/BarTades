package com.bartades.controller;

import com.bartades.dao.UsuarioDAO;
import com.bartades.model.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioController {

	public static boolean adicionarUsuario(Usuario user) throws ClassNotFoundException, SQLException {
		return UsuarioDAO.SalvarUsuario(user);
	}

	public static boolean atualizarUsuario(Usuario user) throws ClassNotFoundException, SQLException {
		return UsuarioDAO.AtualizarUsuario(user);
	}

	public static ArrayList<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException {
		
		return UsuarioDAO.listarUsuarios();
	}

	public static ArrayList<Usuario> encontrarUsuarioPorId(int id) throws ClassNotFoundException, SQLException {
		return UsuarioDAO.encontrarUsuarioPorId(id);
	}
}
