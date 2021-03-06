<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="/static/css/application.css">
    <link rel="icon" type="image/png" href="/static/favicon.ico" />
    <title>Clients</title>
</head>
<body>
	<header id="header">
		<img alt="Cocktails" src="${pageContext.request.contextPath}/static/cocktails.png" />
		<h1>Spring bar</h1>
	</header>
	<section id="content">
		<h2>Liste des clients</h2>
		<table>
		<tr>
			<th>Id</th>
			<th>Nom</th>
		</tr>
		<c:forEach items="${clients}" var="client">
			<c:url var="url" value="client/${client.id}" />
			<tr>
				<td><a href="${url}">${client.nom}</a></td>
				<td>${client.email}</td>
			</tr>
		</c:forEach>
		</table>
	</section>
	<footer id="footer">
		<a href="<c:url value="/accueil" />">Accueil</a> | <a href="<c:url value="/utilisateur" />">${sessionScope.utilisateur.nom}</a>
	</footer>
</body>
</html>