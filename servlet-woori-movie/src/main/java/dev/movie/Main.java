package dev.movie;

import dev.movie.service.MainService;
import dev.service.cloud.Console;

public class Main {

	public static void main(String[] args) throws Exception {
		Console.writeln("🎬 어서오세요! <우리 극장>입니다! 🎬");

		MainService.showMovieList();
	    MainService.showTimeList();
		MainService.showSeatList();
		MainService.selectPay();
		MainService.printTicket();
	}

}
