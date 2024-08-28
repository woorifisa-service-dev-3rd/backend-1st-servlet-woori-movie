package dev.movie.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.movie.model.dao.SeatDAO;

public class TicketController implements Controller{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String payment = request.getParameter("payment");
		
		HttpSession session = request.getSession();
		
		session.setAttribute("payment", payment);
		String movieId = (String) session.getAttribute("movieId");
		Long LongMovieId = Long.parseLong(movieId);
		int col = Integer.parseInt((String) session.getAttribute("col"));
		String row = (String) session.getAttribute("row");
		SeatDAO.insertSeat(LongMovieId, col, row);
		
		String url = "/WEB-INF/ticket.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
	}

}
