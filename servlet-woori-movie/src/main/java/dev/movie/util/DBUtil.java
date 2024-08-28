package dev.movie.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import dev.service.cloud.DBConfigurer;

public class DBUtil {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_NAME = "woori_movie";
	private static final String USER = "root";
	private static final String PASSWORD = "1234";
	
	private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	// Java와 MySQL Database 연결
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASSWORD);
		return connection;
	}
	
	public static void close(Connection con, PreparedStatement pstmt, ResultSet rset) {
		try {
			if (rset != null) {
				rset.close();
				rset = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void close(Connection con, PreparedStatement pstmt) {
		try {

			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

