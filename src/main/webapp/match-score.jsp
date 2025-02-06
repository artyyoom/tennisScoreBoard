<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome to tennis!</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <table>
            <tbody>
                <tr>
                    <td>${currentMatch.getFirstPlayer().getName()}</td>
                    <td>${currentMatch.getFirstScore().getSet()}</td>
                    <td>${currentMatch.getFirstScore().getGame()}</td>
                    <td>${currentMatch.getFirstScore().getPoint()}</td>
                    <td>${currentMatch.getSecondPlayer().getName()}</td>
                    <td>${currentMatch.getSecondScore().getSet()}</td>
                    <td>${currentMatch.getSecondScore().getGame()}</td>
                    <td>${currentMatch.getSecondScore().getPoint()}</td>

                    <form action="match-score?uuid=${currentMatch.getUuid()}" method="post">
                    
                        <button type="submit" name="winnerId" value="${currentMatch.getFirstPlayer().getId()}">
                            First Player Won
                        </button>

                        <button type="submit" name="winnerId" value="${currentMatch.getSecondPlayer().getId()}">
                            Second Player Won
                        </button>
                    </form>
                </tr>
            </tbody>
        </table>


    </body>
</html>
