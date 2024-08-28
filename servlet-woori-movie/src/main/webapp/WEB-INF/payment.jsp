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
	<c:when
			test="${empty requestScope.price}">
			<p>결제 불가</p>
		</c:when>
		<c:otherwise>
			<p>선택한 좌석: ${row} ${col}</p>
		<p>결제금액: ${price}</p>
		
			<form action="ticket" method="POST">
				<p>결제 수단을 선택하세요.</p>
				<p>- 현금</p>
				<p>- 카드</p>
				<input type="text" name="payment" />
				<button type="submit">선택</button>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>