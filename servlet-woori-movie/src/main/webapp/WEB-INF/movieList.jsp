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
		<c:when test="${empty requestScope.movieList || fn: length(movieList) == 0 }">
			<p>등록된 영화 정보가 없습니다!</p>
		</c:when>
		<c:otherwise>
			<c:forEach items="${requestScope.movieList}" var="movie">
				<p>${movie}</p>
			</c:forEach>
			<form action="time" method="POST">
				<input type="text" name="movieTitle" />
				<button type="submit">선택</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>