<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>mouseList.jsp</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
</head>
<body>
	<%
	String title = (String) session.getAttribute("title");
	String time = (String) session.getAttribute("time");
	String row = (String) session.getAttribute("row");
	String col = (String) session.getAttribute("col");
	String payment = (String) session.getAttribute("time");
	int price = (int) session.getAttribute("price");
	%>
	<span>-- ticket --</span><br>
	<span>영화명: <%=title%></span><br>
	<span>시간: <%=time%></span><br>
	<span>좌석: <%=row%><%=col%></span><br>
	<span>결제수단: <%=payment%></span><br>
	<span>가격: <%=price%></span><br>
	<span>즐거운 관람 되세요 : )</span>
</body>
</html>