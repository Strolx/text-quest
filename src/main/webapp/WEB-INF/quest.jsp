<%@ page import="com.javarush.kvon.services.QuestService" %>
<%@ page import="com.javarush.kvon.models.Type" %><%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.07.2023
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles/bottom-left.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <title>Title</title>
</head>
<body>
<c:set var="quest" value="<%= (QuestService) request.getSession().getAttribute(\"quest\")%>"/>
<% if (!((QuestService) request.getSession().getAttribute("quest")).isQuestOver()) { %>
<form action="/quest" method="post">
    <h1><%= ((QuestService) request.getSession().getAttribute("quest")).getProposal().replace("\n", "<br>") %>
    </h1>
    <br>
    <input type="radio" id="first_option" name="answer" value=1 checked>
    <label for="first_option"><%= ((QuestService) request.getSession().getAttribute("quest")).getTextFirstOption() %>
    </label>
    <br>
    <input type="radio" id="second_option" name="answer" value=2>
    <label for="second_option"><%= ((QuestService) request.getSession().getAttribute("quest")).getTextSecondOption() %>
    </label>
    <br>
    <input type="submit" value="Ответить"/>
</form>
<% } else {
    HttpSession currentSession = request.getSession();
    int numberOfPlayedGames = (Integer) currentSession.getAttribute("numberOfPlayedGames");
    numberOfPlayedGames++;
    currentSession.setAttribute("numberOfPlayedGames", numberOfPlayedGames);
    QuestService quest = (QuestService) currentSession.getAttribute("quest");
    String question = quest.getProposal();
    String result = quest.getResultOfQuest();
    if (result.equals(Type.WINNING.getText())) {
        int numberOfGamesWon = (Integer) currentSession.getAttribute("numberOfGamesWon");
        numberOfGamesWon++;
        currentSession.setAttribute("numberOfGamesWon", numberOfGamesWon);
    }%>
<h1><%= question %>
</h1>
<h1><%= result %>
</h1>
<form action="/restart" method="post">
    <input type="submit" value="Сыграть ещё раз"/>
</form>
<% } %>
<div id="bottom-left">
    <p>Статистика:</p>
    <p>IP address: <%= request.getSession().getAttribute("ipAddress") %>
    </p>
    <p>Имя в игре: <%= request.getSession().getAttribute("name") %>
    </p>
    <p>Количество игр: <%= request.getSession().getAttribute("numberOfPlayedGames") %>
    </p>
    <p>Количество побед: <%= request.getSession().getAttribute("numberOfGamesWon") %>
    </p>
</div>
</body>
</html>
