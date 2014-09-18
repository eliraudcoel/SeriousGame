<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		
		<!-- JQuery -->
		<script src="javascript/jquery-1.11.0.min.js"></script>
		<script src="javascript/jquery-migrate-1.2.1.min.js"></script>
		
		<!-- Javascript -->
		<script src="javascript/produits.js"></script>
		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
		
		<!-- CSS -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" href="css/style_in_site.css">
		
		<title>Jeu d'entreprise</title>
	</head>
	<body>
		<%@page import="Modeles.Utilisateur"%>
		
		<%
			Utilisateur utilisateur;
			if(request.getAttribute("user") != null) {
				utilisateur = (Utilisateur) request.getAttribute("user"); 
				session.setAttribute("user", utilisateur);
			} else {
				utilisateur = (Utilisateur) session.getAttribute("user"); 
			}
		%>
		
		<% if(utilisateur.is_admin()) { %>
			<%@ include file="header_admin.jsp"  %>
		<% } else { %>
			<%@ include file="header.jsp"  %>
		<% } %>
	    
        <div class="container">