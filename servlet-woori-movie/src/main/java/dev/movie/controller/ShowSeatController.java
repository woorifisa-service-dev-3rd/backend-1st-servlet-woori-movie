package dev.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.movie.model.dao.MovieDAO;
import dev.movie.model.dto.MovieTime;
import dev.movie.service.SeatService;

public class ShowSeatController implements Controller{
	
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String movieId = request.getParameter("movieId");			
			String[] seatList = SeatService.getAllSeat(Long.parseLong(movieId));
			String time = SeatService.getTime(movieId);
			request.setAttribute("seatList", seatList);
			HttpSession session = request.getSession();
			
			session.setAttribute("time", time);
			session.setAttribute("movieId", movieId);
			
			String[] priceList = new String[5];
			priceList[0] = "A: 16,000원";
			priceList[1] = "B: 18,000원";
			priceList[2] = "C: 18,000원";
			priceList[3] = "D: 18,000원";
			priceList[4] = "E: 16,000원";
			request.setAttribute("priceList", priceList);
			
			// JSP 페이지로 포워딩
			String url = "/WEB-INF/seatList.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
			response.setStatus(200);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
