<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>mouseList.jsp</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<style>
p {
	margin: 0;
}
</style>
<body>
	<c:choose>
		<c:when
			test="${empty requestScope.seatList || fn: length(seatList) == 0 }">
			<p>등록된 좌석 정보가 없습니다!</p>
		</c:when>
		<c:otherwise>
			<p>가격표</p>
			<c:forEach items="${requestScope.priceList}" var="price">
				<p>${price}</p>
			</c:forEach>

			<div style="margin-top:20px;">
				<p>좌석표</p>
				<c:forEach items="${requestScope.seatList}" var="seat">
					<p>${seat}</p>
				</c:forEach>
			</div>
			<form action="pay" method="POST" style="margin-top:20px;">
				<p>
					행 입력 : <input type="text" name="seatRow" />
				</p>
				<p>
					열 입력 : <input type="text" name="seatCol" />
				</p>
				<button type="submit">선택</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>