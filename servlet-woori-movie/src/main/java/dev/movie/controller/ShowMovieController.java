package dev.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.movie.model.dao.MovieDAO;

public class ShowMovieController implements Controller{

	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<String> titles = MovieDAO.findAll();
			
			request.setAttribute("movieList", titles);
			
			// JSP 페이지로 포워딩
			String url = "/WEB-INF/movieList.jsp";
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
