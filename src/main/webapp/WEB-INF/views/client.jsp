<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/static/css/application.css">
    <link rel="icon" type="image/png" href="/static/favicon.ico" />
    <title>Informations client</title>
</head>
<body>
	<header id="header">
		<img alt="Cocktails" src="${pageContext.request.contextPath}/static/cocktails.png" />
		<h1>Spring bar</h1>
	</header>
	<section id="content">
		<h2>Client n<sup>o</sup> ${client.id}</h2>
		<ul>
			<li><label>Titre :</label> ${client.titre.libelle}</li>
			<li><label>Nom :</label> ${client.nom}</li>
			<li><label>Email :</label> ${client.email}</li>
			<li><label>Date de naissance :</label> <fmt:formatDate value="${client.dateNaissance}" pattern="dd MMMM yyyy"/></li>
		</ul>
	</section>
	<c:if test="${modification}">
		<p class="success">Le client a été modifié avec succès</p>
	</c:if>
	<footer id="footer">
		<a href="<c:url value="/accueil" />">Accueil</a> | <a href="<c:url value="/client/${client.id}/modification" />">Modification</a> | <a href="<c:url value="/utilisateur" />">${sessionScope.utilisateur.nom}</a>
	</footer>
</body>
</html>