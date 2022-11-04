<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title> Too taah togloom</title>
</head>
<body>
<h1> Too taah togloom</h1>
<hr>
<h3>${message }</h3>

<c:if test="${sessionScope.count != null}">
  <form method="get" action="guess">
    1ees 100 hurtel too oruulna uu?<br>
    <input type="text" name="number"><br>
    <input type="submit" value="Submit">
  </form>
</c:if>

<a href="guess">Dahin ehluuleh</a>
</body>
</html>