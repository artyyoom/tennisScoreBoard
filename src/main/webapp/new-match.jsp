<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>

<html lang="en">
    <head>
        <title>Create a new match</title>
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <h2>Введите данные:</h2>
        <form action="new-match" method="post">
            <label for="player1">player1</label><br>
            <input type="text" id="player1" name="player1" required><br><br>

            <label for="player2">player2</label><br>
            <input type="text" id="player2" name="player2" required>

            <input type="submit" value="start game">
        </form>
    </body>
</html>