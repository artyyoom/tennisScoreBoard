<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tennis Scoreboard | Finished Matches</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">

    <script src="js/app.js"></script>
</head>

<body>
<header class="header">
    <section class="nav-header">
        <div class="brand">
            <div class="nav-toggle">
                <img src="images/menu.png" alt="Logo" class="logo">
            </div>
            <span class="logo-text">TennisScoreboard</span>
        </div>
        <div>
            <nav class="nav-links">
                <a class="nav-link" href="#">Home</a>
                <a class="nav-link" href="#">Matches</a>
            </nav>
        </div>
    </section>
</header>
<main>
    <div class="container">
        <h1>Matches</h1>
        <div class="input-container">
            <form class="input-container" action="matches?filter_by_player_name=${filter_by_player_name}" method="get">
                <input class="input-filter" placeholder="Filter by name" type="text" name="filter_by_player_name"/>
                <button class="btn-filter">Reset Filter</button>
            </form>
        </div>

        <table class="table-matches">
            <tr>
                <th>Player One</th>
                <th>Player Two</th>
                <th>Winner</th>
            </tr>
            <c:forEach items="${matches}" var="match">
                <tr>
                    <td>${match.getPlayer1().getName()}</td>
                    <td>${match.getPlayer2().getName()}</td>
                    <td>${match.getWinner().getName()}</td>
                </tr>
            </c:forEach>
        </table>

        <div class="pagination">

            <c:if test="${page > 1}">
                <a class="prev" href="matches?page=${page - 1}">&lt; </a>
            </c:if>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <c:choose>
                  <c:when test="${i == page}">
                    <a class="num-page current" href="matches?page=${i}">${i}</a>
                  </c:when>
                  <c:otherwise>
                    <a class="num-page" href="matches?page=${i}">${i}</a>
                  </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${page < totalPages}">
                <a class="prev" href="matches?page=${page + 1}"> &gt;</a>
            </c:if>
        </div>
    </div>
</main>
<footer>
    <div class="footer">
        <p>&copy; Tennis Scoreboard, project from <a href="https://zhukovsd.github.io/java-backend-learning-course/">zhukovsd/java-backend-learning-course</a>
            roadmap.</p>
    </div>
</footer>
</body>
</html>