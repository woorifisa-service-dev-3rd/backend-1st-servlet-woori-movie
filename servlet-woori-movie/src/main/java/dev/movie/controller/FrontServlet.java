package dev.movie.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reservation/*")
public class FrontServlet extends HttpServlet{
	private Map<String, Controller> controllerMap = new HashMap<>();
	
	public FrontServlet() {
		controllerMap.put("/movie", new ShowMovieController());
		controllerMap.put("/time", new ShowTimeController());
		controllerMap.put("/seat", new ShowSeatController());
		controllerMap.put("/pay", new PayController());
		controllerMap.put("/ticket", new TicketController());
		// controllerMap.put();
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 대부분의 컨트롤러에서 수행되는 공통 처리 수행
		request.setCharacterEncoding("UTF-8");
		
		// 사용자의 요청 URI 값을 확인
		String path = request.getPathInfo();
		System.out.println(path);
		
		// URI 별 특정 요청을 수행할 개별 컨트롤러 불러오기
		Controller controller = controllerMap.get(path);
		// 실제 개별 컨트롤러에게 요청을 처리하도록 호출
		try {
			controller.process(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
} 
