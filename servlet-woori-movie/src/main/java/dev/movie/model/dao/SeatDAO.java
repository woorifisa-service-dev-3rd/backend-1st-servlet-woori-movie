package dev.movie.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dev.movie.model.dto.Seat;
import dev.movie.util.DBUtil;

public class SeatDAO {
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	public static List<Seat> findAllSeat(Long movieId) throws Exception {
		final String selectQuery = "SELECT * FROM seat WHERE movie_id = ? order by row_id";

		List<Seat> seats = new ArrayList<>();
  
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setLong(1, movieId);

			try (ResultSet rs = pstmt.executeQuery();) {

				while(rs.next()) {
					int id = rs.getInt("id");
					int col = rs.getInt("col");
					int sel_moive_id = rs.getInt("movie_id");
					int row_id = rs.getInt("row_id");
					
					seats.add(new Seat(id, col, sel_moive_id, row_id));
				}
			}
		
			return seats;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean findSeat(Long movieId, int col, String row) throws Exception {
		final String selectQuery = "SELECT * FROM seat WHERE movie_id = ? and col = ? and row_id = (select id from seat_row where name = ?)";

		boolean isEmpty = true;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setLong(1, movieId);
			pstmt.setInt(2, col);
			pstmt.setString(3, row);

			try (ResultSet rs = pstmt.executeQuery();) {

				if (rs.next()) {
					isEmpty = false; 
				}
			}
		
			return isEmpty;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isEmpty;
	}
	
	public static boolean insertSeat(Long movieId, int col, String row) throws Exception {
		final String selectQuery = "INSERT INTO seat(movie_id, col, row_id) VALUES(?, ?, (select id from seat_row where name = ?))";

		boolean isSuccess = false;

		try (Connection connection = DBUtil.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(selectQuery);) {

			pstmt.setLong(1, movieId);
			pstmt.setInt(2, col);
			pstmt.setString(3, row);
			
			boolean result = pstmt.execute();
			if(!result) {
				isSuccess = true;
			}
			return isSuccess;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isSuccess;
	}
}
