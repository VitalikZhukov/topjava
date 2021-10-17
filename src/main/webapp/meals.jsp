<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<table border="2">
    <caption>Meal list.</caption>
    <thead>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    </thead>
    <colgroup style="background-color: #ddd;">
        <col>
        <col style="background-color: #ddd;">
        <col>
    </colgroup>
    <tbody>
        <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
        <c:forEach var="meal" items="${mealList}">

            <c:set var="colorMeal" value="green"/>
            <c:if test="${meal.excess}">
                <c:set var="colorMeal" value="red"/>
            </c:if>

            <tr style="color: ${colorMeal}">
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </tr>
        </c:forEach>
    </tbody>

</table>

</body>
</html>