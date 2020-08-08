package jdev01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdev01.bean.Produto;
import jdev01.connection.SingleConnection;

public class DaoProdutos {
	private static Connection conn;

	public DaoProdutos() {
		// TODO Auto-generated constructor stub
		this.conn = SingleConnection.getConn();

	}
	public void salvar(Produto produto) {
		String sql = "insert into produto(nome,quantidade,valor) values (?,?,?)";
		try {
			PreparedStatement insert = conn.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setInt(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
			insert.execute();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();

			}
		}

	}

	public List<Produto> listar() throws Exception {
		String sql = "select * from produto";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		List<Produto> listar = new ArrayList<>();
		while (resultSet.next()) {
			Produto produto = new Produto();

			produto.setNome(resultSet.getString("nome"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setValor(resultSet.getDouble("valor"));
			produto.setId(resultSet.getInt("id"));
			listar.add(produto);
		}
		return listar;

	}

	public void delete(String id) {
		String sql = "delete from produto where id = ?";
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

	public Produto consultar(String id) throws Exception {
		String sql = "select * from produto where id = ?";
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				Produto produto = new Produto();
				produto.setNome(resultSet.getString("nome"));
				produto.setQuantidade(resultSet.getInt("quantidade"));
				produto.setValor(resultSet.getDouble("valor"));
				produto.setId(resultSet.getInt("id"));
				return produto;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return null;

	}

	public boolean validarNome(String login) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = ?";
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
	public boolean validarNomeUpdate(String login, String id) throws Exception {
		String sql = "select count(1) as qtd from produto where nome = ? and id <> ?";
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


	public void atualizar(Produto produto) {
		String sql = "update produto set nome = ?, quantidade = ?, valor = ? where id = " + produto.getId();
		try {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, produto.getNome());
			statement.setInt(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());
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
