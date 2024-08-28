package dev.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class PayController implements Controller {
	@Override
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			String row = request.getParameter("seatRow");
			String col = request.getParameter("seatCol");
			int price = 0;
			if(row.equals("A")) price = 16000;
			else if(row.equals("E")) price = 16000;
			else price = 18000;
			
			request.setAttribute("row", row);
			request.setAttribute("col", col);
			request.setAttribute("price", price);
			
			HttpSession session = request.getSession();
			session.setAttribute("row", row);
			session.setAttribute("col", col);
			session.setAttribute("price", price);
			
			// JSP 페이지로 포워딩
			String url = "/WEB-INF/payment.jsp";
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
