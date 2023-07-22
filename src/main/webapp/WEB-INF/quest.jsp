<%@ page import="com.javarush.kvon.models.State" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.07.2023
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/styles/bottom-left.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Космические приключения</title>
</head>
<body>

<c:if test="${!sessionScope.quest.isQuestOver()}" var="isQuestNotOver" scope="request">
    <form action="/quest" method="post">
        <h1>
            ${sessionScope.quest.getProposal()}
        </h1>
        <br>
        <input type="radio" id="first_option" name="answer" value=1 checked>
        <label for="first_option">
            ${sessionScope.quest.getTextFirstOption()}
        </label>
        <br>
        <input type="radio" id="second_option" name="answer" value=2>
        <label for="second_option">
            ${sessionScope.quest.getTextSecondOption()}
        </label>
        <br>
        <input type="submit" value="Ответить"/>
    </form>
</c:if>
<c:if test="${!requestScope.isQuestNotOver}">
    <c:set var="numberOfPlayedGames" value="${sessionScope.numberOfPlayedGames+1}" scope="session"/>
    <c:if test="${sessionScope.quest.getStateOfQuest() == State.WINNING}">
        <c:set var="numberOfGamesWon" value="${sessionScope.numberOfGamesWon+1}" scope="session"/>
    </c:if>
    <h1>
        ${sessionScope.quest.getProposal()}
    </h1>
    <h1>
        ${sessionScope.quest.getResultOfQuest()}
    </h1>
    <form action="/restart" method="post">
        <input type="submit" value="Сыграть ещё раз"/>
    </form>
</c:if>
<div id="bottom-left">
    <p>Статистика:</p>
    <p>IP address: ${sessionScope.ipAddress}
    </p>
    <p>Имя в игре: ${sessionScope.name}
    </p>
    <p>Количество игр: ${sessionScope.numberOfPlayedGames}
    </p>
    <p>Количество побед: ${sessionScope.numberOfGamesWon}
    </p>
</div>
</body>
</html>
