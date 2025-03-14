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
                <a class="nav-link" href="index.jsp">Home</a>
                <a class="nav-link" href="matches">Matches</a>
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
            <c:choose>
                <c:when test="${matchesIsEmpty == true}">
                    <h2>matches not found</h2>
                </c:when>
                <c:otherwise>
                    <c:if test="${page > 1}">
                        <a class="prev" href="matches?page=${page - 1}&filter_by_player_name=${filter_by_player_name}">&lt;</a>
                    </c:if>

                    <p class="num-page">${page} ... ${totalPages}</p>

                    <c:if test="${page < totalPages}">
                        <a class="prev" href="matches?page=${page + 1}&filter_by_player_name=${filter_by_player_name}">&nbsp; &gt;</a>
                    </c:if>
                </c:otherwise>
            </c:choose>
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