package jdev01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.StampedLock;

import jdev01.bean.Usuario;
import jdev01.connection.SingleConnection;

public class DaoUsuario {
	private static Connection conn;

	public DaoUsuario() {
		// TODO Auto-generated constructor stub
		this.conn = SingleConnection.getConn();

	}

	public void salvar(Usuario usuario) {
		String sql = "insert into usuario(login,senha,nome,fone) values (?,?,?,?)";
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			insert.execute();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();

			}
		}

	}

	public List<Usuario> listar() throws Exception {
		String sql = "select * from usuario";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<Usuario> listar = new ArrayList<>();
		while (resultSet.next()) {
			Usuario usuario = new Usuario();
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setId(resultSet.getInt("id"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setFone(resultSet.getString("fone"));
			listar.add(usuario);
		}
		return listar;

	}

	public void delete(String id) {
		String sql = "delete from usuario where id = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public Usuario consultar(String id) throws Exception {
		String sql = "select * from usuario where id = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Usuario beanCursoJsp = new Usuario();
				beanCursoJsp.setLogin(resultSet.getString("login"));
				beanCursoJsp.setSenha(resultSet.getString("senha"));
				beanCursoJsp.setId(resultSet.getInt("id"));
				beanCursoJsp.setNome(resultSet.getString("nome"));
				beanCursoJsp.setFone(resultSet.getString("fone"));
				return beanCursoJsp;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return null;

	}

	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				return resultSet.getInt("qtd") <= 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return false;

	}
	public boolean validarLoginUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from usuario where login = ? and id <> ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, login);
			statement.setString(2, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {

				return resultSet.getInt("qtd") <= 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return false;

	}


	public void atualizar(Usuario usuario) {
		String sql = "update usuario set login = ?, senha = ?, nome = ?, fone = ? where id = " + usuario.getId();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getFone());
			statement.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
