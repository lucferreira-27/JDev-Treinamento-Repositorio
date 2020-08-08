package jdev01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jdev01.connection.SingleConnection;

public class DaoLogin {
	private Connection conn;

	public DaoLogin() {
		// TODO Auto-generated constructor stub
		this.conn = SingleConnection.getConn();
	}

	public boolean validarLogin(String login, String senha) throws Exception {

		String sql = "select * from usuario where login = ? and senha = ?";
		PreparedStatement statment = conn.prepareStatement(sql);
		statment.setString(1, login);
		statment.setString(2, senha);
		ResultSet resultSet = statment.executeQuery();

		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}

	}

}
