<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/static/css/application.css">
    <link rel="icon" type="image/png" href="/static/favicon.png" />
    <title>Informations client</title>
</head>
<body>
	<header id="header">
		<img alt="Cocktails" src="${pageContext.request.contextPath}/static/cocktails.png" />
		<h1>Spring bar</h1>
	</header>
	<section id="content">
		<h2>Client n<sup>o</sup> ${client.id} : modification</h2>
		<form:form action="/client/${client.id}/modification" modelAttribute="client">
		<ul>
			<li><label>Titre :</label> <form:select path="titre"><form:options itemLabel="libelle"/></form:select></li>
			<li><label>Nom :</label> <form:input path="nom" /></li>
			<li><label>Email :</label> <form:input path="email" /></li>
			<li><label>Date de naissance :</label> <form:input path="dateNaissance" /></li>
		</ul>
		<button type="submit">Enregistrer</button>
		</form:form>
	</section>
	<footer id="footer">
		<a href="<c:url value="/accueil" />">Accueil</a> | <a href="<c:url value="/client/${client.id}" />">Client</a> | <a href="<c:url value="/utilisateur" />">${sessionScope.utilisateur.nom}</a>
	</footer>
</body>
</html>