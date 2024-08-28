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

public class ShowTimeController implements Controller{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("movieTitle");
			
			List<MovieTime> timeList = MovieDAO.findAllByTitle(title);
			
			request.setAttribute("timeList", timeList);
			
			HttpSession session = request.getSession();
			
			session.setAttribute("title", title);
			
			// JSP 페이지로 포워딩
			String url = "/WEB-INF/timeList.jsp";
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
