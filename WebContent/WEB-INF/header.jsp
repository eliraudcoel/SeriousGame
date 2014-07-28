<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="inc/style.css">
		
		<!-- Ajout de la librairie BootStrap cf : http://getbootstrap.com/ -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<title>Jeu d'entreprise</title>
	</head>
	<body>		
		<!-- Menu -->
		<div class="container-fluid">
			<nav class="navbar navbar-inverse row" role="navigation">
	          <ul class="nav navbar-nav">
	            <li>
	            	<a href="Application?action=home" class="col-xs-12">
	            		<span class="glyphicon glyphicon-dashboard"></span> Home
	            	</a>
	            </li>
	            <li>
	            	<a href="Parties?action=index" class="col-xs-12">
	            		<span class="glyphicon glyphicon-tower"></span> Liste de Partie
	            	</a>
	            </li>
	            <li>
	            	<a href="Application?action=disconnect" class="col-xs-12">
	            		<span class="glyphicon glyphicon-off"></span> Déconnexion
	            	</a>
	            </li>
	          </ul>
	        </nav>
	    </div>
        <div class="container">