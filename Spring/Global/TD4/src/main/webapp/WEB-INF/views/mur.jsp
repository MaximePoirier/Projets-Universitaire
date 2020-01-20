<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Le mur (visualisé par ${pseudo})!</title>
</head>
<body>

<ul>
<c:forEach items="${messages}" var="message">
    <li>
        ${message.pseudo} a écrit :
        <ul>
           <c:forEach items="${message.messages}" var="msg">
               <li>${msg}</li>
           </c:forEach>
        </ul>
    </li>
</c:forEach>
</ul>

<form action="rafraichir">
    <button type="submit">Rafraichir</button>
</form>

<form action="ecrire" method="post">
    Nouveau message : <input type="text" name="message"/>
    <button type="submit">Publier</button>
</form>

<form action="filtrer" method="post">
    Nouveau filtre : <input type="text" name="filtre"/>
    <button type="submit">Filtrer</button>
</form>

<form action="update" method="post">
    Nouveau login : <input type="text" name="newlogin"/>
    <button type="submit">Modifier</button>
</form>

</body>
</html>
