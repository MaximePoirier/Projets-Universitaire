<%--
  Created by IntelliJ IDEA.
  User: exbrayat
  Date: 18/09/18
  Time: 17:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Hello ${courant.surnom}</title>

    <script type="application/javascript">
        function participer(ip) {
            // rustique : on soumet un formulaire...
            var form = document.createElement("form");
            form.setAttribute("method", "POST");
            form.setAttribute("action", "http://localhost:8080/TD2_war_exploded/");

            var intp = document.createElement("input");
            intp.setAttribute("type", "hidden");
            intp.setAttribute("name", "intituleP");
            intp.setAttribute("value", ip);
            form.appendChild(intp);

            var todo=document.createElement("input");
            todo.setAttribute("type", "hidden");
            todo.setAttribute("name", "TODO");
            todo.setAttribute("value", "participer");
            form.appendChild(todo);

            //Ajout du formulaire à la page et soumission du formulaire
            document.body.appendChild(form);
            form.submit();
        }
        function Delogger() {
            var form = document.createElement("form");
            form.setAttribute("method","POST");
            form.setAttribute("action","http://localhost:8080/TD2_war_exploded/");

            var todo=document.createElement("input");
            todo.setAttribute("type", "hidden");
            todo.setAttribute("name", "TODO");
            todo.setAttribute("value", "delogger");
            form.appendChild(todo);

            document.body.appendChild(form);
            form.submit();
        }
    </script>
</head>
<body>

<p>Salut à toi, O ${courant.surnom}. <button type="submit" onclick="Delogger()">Déconnexion</button>
</p>
<p>
Ton mot de passe est ${courant.motdepasse}. (comment ça, c'est une info privée?)
</p>

Tes compétences sont :
<ul>
    <c:forEach items="${courant.declare}" var="comp">
        <li> ${comp.deType.intituleC}, niveau ${comp.niveau} (${comp.commentaire})</li>
    </c:forEach>
</ul>
Les autres compétences sont :
<ul>
    <c:if test="${empty autreCompetence}">
        <p><i>Tu possèdes déjà toutes les compétences disponible !</i></p>
    </c:if>
    <c:forEach items="${autreCompetence}" var="acomp">
        <li>${acomp.intituleC}, ${acomp.descriptionC}
            <form method="post" action="http://localhost:8080/TD2_war_exploded/">
                <label id="niveau">Niveau<input type="number" name="niveau"/></label>
                <label id="commentaire">Commentaire<input type="text" name="commentaire"/></label>
                <input type="hidden" name="intituleC" value="${acomp.intituleC}"/>
                <input type="hidden" name="TODO" value="competence" />
                <button type="submit">Ajouter Compétence</button>
            </form>
        </li>
    </c:forEach>
</ul>

Les projets :
<ul>
    <li> dont tu es responsable :
        <c:if test="${empty courant.responsable}">
            <p><i>Liste vide</i></p>
        </c:if>
        <c:if test="${not empty courant.responsable}">
        <ul>
            <c:forEach items="${courant.responsable}" var="projet">
                <li> ${projet.intituleP} : ${projet.descriptionP}</li>
            </c:forEach>
        </ul>
            <p/>

        </c:if>
    </li>
    <li> dont tu es simple participant :
        <c:if test="${empty courant.participe}">
            <p><i>Liste vide</i></p>
        </c:if>
        <c:if test="${not empty courant.participe}">
        <ul>
            <c:forEach items="${courant.participe}" var="projet">
                <li> ${projet.intituleP} : ${projet.descriptionP}</li>
            </c:forEach>
        </ul>
            <p/>

        </c:if>
    </li>
    <li> pour lesquels tu es compétent :
        <c:if test="${empty competent}">
            <p><i>Tu participes déjà à tous les projets pour lesquels tu es compétent...</i></p>
        </c:if>
        <c:if test="${not empty competent}">
        <ul>
            <c:forEach items="${competent}" var="projet">
                <li> ${projet.intituleP} : ${projet.descriptionP} <button type="submit" onclick="participer('${projet.intituleP}');">participer</button></li>
            </c:forEach>
        </ul>
            <p/>
        </c:if>
    </li>

    <li> les autres projets :
        <c:if test="${empty autres}">
            <p><i>Liste vide</i></p>
        </c:if>
        <c:if test="${not empty autres}">
        <ul>
            <c:forEach items="${autres}" var="projet">
                <li> ${projet.intituleP} : ${projet.descriptionP}</li>
            </c:forEach>
        </ul>
            <p/>

        </c:if>
    </li>
</ul>
Ajouter un projet :
<form method="post" action="http://localhost:8080/TD2_war_exploded/">
    <label id="nom">Nom<input type="text" name="nom"/></label>
    <label id="description">Description<input type="text" name="description"/></label>
    Les compétences requises pour le projet :
    <c:forEach items="${AllCompetence}" var="comps">
        <label for="${comps.intituleC}">${comps.intituleC}</label><input id="${comps.intituleC}" name="competences[]" type="checkbox" value="${comps.intituleC}">
    </c:forEach>
    <input type="hidden" name="TODO" value="projet" />
    <button type="submit">Ajouter</button>
</form>
<p><c:if test="${not empty checkBase}">
    Attention ! Il y au moins un employé qui participe à un projet pour lequel il n'est pas compétent !
    On ne dira pas le nom du/des coupable(s) ( <c:forEach items="${checkBase}" var="coupable">${coupable.surnom} </c:forEach>)
</c:if>
<c:if test="${empty checkBase}">
    Tout est sous contrôle, les employés sont assez compétent !
</c:if></p>

</body>
</html>
