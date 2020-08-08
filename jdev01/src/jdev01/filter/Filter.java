package jdev01.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import jdev01.connection.SingleConnection;

@WebFilter(urlPatterns = {"/*"})
public class Filter implements javax.servlet.Filter {

	private static Connection conn;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		try{
		chain.doFilter(request, response);
		conn.commit();
		}catch (Exception e) {
			// TODO: handle exception
			try {
				conn.rollback();
			}catch(SQLException e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
		}
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.conn = SingleConnection.getConn();
	}

}
