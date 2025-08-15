<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        table, td, tr{
            border: solid black 1px;
        }

    </style>
</head>
<body>
<h1>Thống Kê Phim</h1>

<h2>Top 3 Phim Rating Cao Nhất</h2>
<table>
    <tr><th>Tiêu Đề</th><th>Đạo Diễn</th><th>Rating</th></tr>
    <c:forEach var="movie" items="${top3Movies}">
        <tr>
            <td>${movie.title}</td>
            <td>${movie.director}</td>
            <td>${movie.rating}</td>
        </tr>
    </c:forEach>
</table>

<h2>Phim Phát Hành Trong Tháng Này</h2>
<table>
    <tr><th>Tiêu Đề</th><th>Đạo Diễn</th><th>Ngày Phát Hành</th></tr>
    <c:forEach var="movie" items="${moviesThisMonth}">
        <tr>
            <td>${movie.title}</td>
            <td>${movie.director}</td>
            <td>${movie.releaseDate}</td>
        </tr>
    </c:forEach>
</table>

<h2>Phim của Đạo Diễn Có Rating Cao Nhất</h2>
<table>
    <tr><th>Tiêu Đề</th><th>Đạo Diễn</th><th>Rating</th></tr>
    <c:forEach var="movie" items="${moviesByTopDirector}">
        <tr>
            <td>${movie.title}</td>
            <td>${movie.director}</td>
            <td>${movie.rating}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>