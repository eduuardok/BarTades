package com.bartades.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bartades.model.Cargo;
import com.bartades.model.Usuario;

public class UsuarioDAO {

	public static Usuario encontrarUsuarioPorEmail(String email) throws SQLException, ClassNotFoundException {

		Usuario u = new Usuario();

		String sql = 	"select u.id, \n" + 
				"		u.nome, \n" + 
				"		u.email, \n" + 
				"		u.telefone, \n" + 
				"		u.cpf, \n" + 
				"		u.sexo, \n" + 
				"		u.senha, \n" + 
				"		un.nome as unidade_atuacao, \n" + 
				"		c.nome as cargo \n" + 
				"		from usuarios u \n" + 
				"		join \n" + 
				"		unidades un on u.unidade_atuacao = un.id \n" + 
				"		join \n" + 
				"		funcoes c on u.cargo = c.id \n" +
				"		where u.email = ?;";

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);) {
			select.setString(1, email);
			ResultSet retorno = select.executeQuery();

			while (retorno.next()) {
				u = new Usuario(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("cpf"),
						retorno.getString("email"), retorno.getString("senha"), retorno.getString("telefone"),
						retorno.getString("sexo"), retorno.getString("unidade_atuacao"), retorno.getString("cargo"));
			}

		}
		return u;
	}

	public static ArrayList<Usuario> encontrarUsuarioPorId(int id) throws ClassNotFoundException, SQLException {

		ArrayList<Usuario> usuarioRetorno = new ArrayList<>();

		String sql = "select u.id,\n" + "u.nome, \n" + "u.email, \n" + "u.telefone, \n" + "u.cpf, \n" + "u.sexo, \n"
				+ "u.senha, \n" + "u.unidade_atuacao, \n" + "u.cargo \n" + "from usuarios u \n" + "where u.id = ?;";

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);) {
			select.setInt(1, id);
			ResultSet retorno = select.executeQuery();

			while (retorno.next()) {
				Usuario u = new Usuario(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("cpf"),
						retorno.getString("email"), retorno.getString("senha"), retorno.getString("telefone"),
						retorno.getString("sexo"), retorno.getString("unidade_atuacao"), retorno.getString("cargo"));
				usuarioRetorno.add(u);
			}

		}
		return usuarioRetorno;
	}

	public static ArrayList<Usuario> listarUsuarios() throws ClassNotFoundException, SQLException {

		ArrayList<Usuario> listaUsuarios = new ArrayList<>();

		String sql = "select u.id, \n" + "u.nome, \n" + "u.cpf, \n" + "u.email, \n" + "u.telefone, \n" + "u.senha, \n"
				+ "u.sexo, \n" + "un.nome as unidade_atuacao, \n" + "f.nome as cargo \n" + "from usuarios u\n"
				+ "join unidades un on un.id = u.unidade_atuacao\n" + "join funcoes f on f.id = u.cargo;";

		try (Connection conn = com.bartades.dao.InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);
				ResultSet retorno = select.executeQuery()) {

			while (retorno.next()) {
				Usuario u = new Usuario(retorno.getInt("id"), retorno.getString("nome"), retorno.getString("cpf"),
						retorno.getString("email"), retorno.getString("senha"), retorno.getString("telefone"),
						retorno.getString("sexo"), retorno.getString("unidade_atuacao"), retorno.getString("cargo"));
				listaUsuarios.add(u);
			}

		}

		return listaUsuarios;
	}

	public static boolean SalvarUsuario(Usuario u) throws ClassNotFoundException, SQLException {

		boolean retorno = false;

		String sql = "INSERT INTO usuarios (nome, email, telefone, cpf, sexo, senha, unidade_atuacao, cargo) values (?, ?, ?, ?, ?, ?, ?, ?);";

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement insert = conn.prepareStatement(sql);) {
			insert.setString(1, u.getNome());
			insert.setString(2, u.getEmail());
			insert.setString(3, u.getTelefone());
			insert.setString(4, u.getCPF());
			insert.setString(5, u.getSexo());
			insert.setString(6, u.getSenha());
			insert.setInt(7, encontrarIdUnidadeAtuacao(u.getUnidadeAtuacao()));
			insert.setInt(8, encontrarIdCargo(u.getCargo()));

			int linhasAfetadas = insert.executeUpdate();

			if (linhasAfetadas > 0) {
				retorno = true;
			}

		}
		return retorno;
	}

	public static boolean AtualizarUsuario(Usuario u) throws ClassNotFoundException, SQLException {

		boolean retorno = false;

		String sql = "UPDATE usuarios set nome = ?, email = ?, telefone = ?, cpf = ?, sexo = ?, senha = ?, unidade_atuacao = ?, cargo = ? WHERE id = ?;";

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement update = conn.prepareStatement(sql);) {
			update.setString(1, u.getNome());
			update.setString(2, u.getEmail());
			update.setString(3, u.getTelefone());
			update.setString(4, u.getCPF());
			update.setString(5, u.getSexo());
			update.setString(6, u.getSenha());
			update.setInt(7, encontrarIdUnidadeAtuacao(u.getUnidadeAtuacao()));
			update.setInt(8, encontrarIdCargo(u.getCargo()));
			update.setInt(9, u.getId());

			int linhasAfetadas = update.executeUpdate();

			if (linhasAfetadas > 0) {
				retorno = true;
			}

		}
		return retorno;
	}

	protected static int encontrarIdUnidadeAtuacao(String nomeUnidade) throws ClassNotFoundException, SQLException {

		String sql = "SELECT id FROM unidades WHERE nome = ?";

		int idUnidade = 0;

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);) {
			select.setString(1, nomeUnidade);
			ResultSet retorno = select.executeQuery();

			while (retorno.next()) {
				idUnidade = retorno.getInt("id");
			}

		}

		return idUnidade;

	}

	private static int encontrarIdCargo(String nomeCargo) throws ClassNotFoundException, SQLException {

		String sql = "SELECT id FROM funcoes WHERE nome = ?";

		int idCargo = 0;

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);) {
			select.setString(1, nomeCargo);
			ResultSet retorno = select.executeQuery();

			while (retorno.next()) {
				idCargo = retorno.getInt("id");
			}

		}

		return idCargo;

	}
	
	public static ArrayList<Cargo> listarCargos() throws ClassNotFoundException, SQLException{
		
		ArrayList<Cargo> listaCargos = new ArrayList<Cargo>();
		
		String sql = "select * from funcoes";

		try (Connection conn = InterfaceConexao.obterConexao();
				PreparedStatement select = conn.prepareStatement(sql);
				ResultSet retorno = select.executeQuery()) {

			while (retorno.next()) {
				Cargo c = new Cargo(retorno.getInt("id"), retorno.getString("nome"), retorno.getInt("nivel_acesso"));
				listaCargos.add(c);
			}
			conn.close();
		}
		
		return listaCargos;
		
	}

}
