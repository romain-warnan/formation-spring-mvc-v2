<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
		<h2>${utilisateur.nom}</h2>
		<strong>${utilisateur.idep}</strong> &ndash; ${utilisateur.email}
		<p>Mes rôles :</p>
		<select multiple="multiple" size="8">
			<c:forEach items="${utilisateur.roles}" var="role">
				<option>${role}</option>
			</c:forEach>
		</select>
	</section>
	<footer id="footer">
		<a href="<c:url value="/accueil" />">Accueil</a> | <a href="<c:url value="/utilisateur" />">${sessionScope.utilisateur.nom}</a>
	</footer>
</body>
</html>