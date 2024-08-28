package dev.movie.service;

import java.util.Iterator;
import java.util.List;

import dev.movie.model.dao.MovieDAO;
import dev.movie.model.dao.SeatDAO;
import dev.movie.model.dto.RowPrice;
import dev.movie.model.dto.Seat;
import dev.movie.model.dto.SelectedSeatDTO;
import dev.service.cloud.Console;

public class SeatService {
	public static void saveSeat(Long movieId, int col, String row) throws Exception {
		SeatDAO.insertSeat(movieId, col, row);
	}
	
	public static SelectedSeatDTO getSeatList(Long movieId) throws Exception {
		Console.writeln("선택하신 시간의 좌석표입니다 🙋🏻‍♀️");

		while (true) {
			prtSeat(movieId);

			Console.writeln();
			SelectedSeatDTO seatRow = selectRow();
			Console.writeln();
			int col = selectCol();

			SelectedSeatDTO mySeat = SelectedSeatDTO.builder().movieId(movieId).row(seatRow.getRow()).col(col)
					.price(seatRow.getPrice()).build();
			
			Console.writeln((seatRow.getRow() + col) + " 좌석으로 결정하시겠습니까? (진행하려면 y를 입력)");
			Console.write("===> ");
			String response = Console.read();
			if(!response.equals("y")) {
				Console.writeln("좌석 결정을 다시 진행합니다.");
				Console.writeln();
				continue;
			}

			if (isEmptySeat(movieId, col, mySeat.getRow())) return mySeat;

			Console.writeln("이미 예약이 된 좌석입니다.");
			Console.writeln();
		}
	}
	
	private static void prtSeat(Long movieId) throws Exception {
		Console.writeln("====SCREEN====");
		//Console.writeln(getAllSeat(movieId));
	}
	
	private static SelectedSeatDTO selectRow() throws Exception {
		List<RowPrice> priceList = PriceService.getPrice();

		while (true) {
			Console.writeln("선택할 좌석의 행을 입력하세요 💬");
			Console.writeln("====가격표====");
			for(RowPrice price : priceList) Console.writeln("➡️  " + price.getRow() + " (₩" + price.getPrice() + ")");

			Console.writeln("-------------------------------------------");

			Console.write("===> ");
			String seatRow = Console.read();

			for (RowPrice price : priceList)
				if (seatRow.equals(price.getRow()))
					return SelectedSeatDTO.builder().row(price.getRow()).price(16000).build();

			Console.writeln("존재하지 않는 행입니다.");
			Console.writeln();
		}
	}

	private static int selectCol() {
		int minCol = 1;
		int maxCol = 6;

		while (true) {
			Console.writeln("선택할 좌석의 열을 입력하세요 💬");
			Console.writeln("-------------------------------------------");

			Console.write("===> ");
			int seatCol = Console.readInt();

			if (seatCol >= minCol && seatCol <= maxCol)
				return seatCol;

			Console.writeln("존재하지 않는 열입니다.");
			Console.writeln();
		}
	}
	
	public static String[] getAllSeat(Long movieId) throws Exception {
		int[] row = { 1, 2, 3, 4, 5 };
		String[] seatStr = new String[row.length];
		
		List<Seat> seatDtos = SeatDAO.findAllSeat(movieId);
		StringBuilder sb = new StringBuilder();

		for (int i=0; i<row.length; i++) {
			String[] col = { " 1", " 2", " 3", " 4", " 5", " 6" };
			sb.append((char) (row[i] + 64) + "|");
			Iterator<Seat> iter = seatDtos.iterator();

			while (iter.hasNext()) {
				Seat seat = (Seat) iter.next();
				if (row[i] == seat.getRowId()) {
					col[seat.getCol() - 1] = " X";
				}
			}
			for (String s : col) {
				sb.append(s);
			}
			if (row[i] != 5) {
				sb.append("\n");
			}
			seatStr[i] = sb.toString();
			sb = new StringBuilder();
		}
		
		return seatStr;
	}
        
	private static boolean isEmptySeat(Long movieId, int col, String row) throws Exception {
		return SeatDAO.findSeat(movieId, col, row);
	}

	public static String getTime(String movieId) throws ClassNotFoundException {
		String time = MovieDAO.findByTimeToMovieId(movieId);
		return time;
	}
}
