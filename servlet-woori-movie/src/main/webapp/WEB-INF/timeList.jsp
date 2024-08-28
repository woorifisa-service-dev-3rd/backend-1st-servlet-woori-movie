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
	<c:choose>
		<c:when test="${empty requestScope.timeList || fn: length(timeList) == 0 }">
			<p>등록된 시간 정보가 없습니다!</p>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.timeList}" var="time">
				<p>${time.id} | ${time.time}</p>
			</c:forEach>
			<form action="seat" method="POST">
				<input type="text" name="movieId" />
				<button type="submit">번호 선택</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>