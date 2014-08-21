<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" type="text/css" href="css/style_in_site.css">
		
		<!-- JQuery -->
		<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
		
		<script src="javascript/produits.js"></script>
		
		<!-- Ajout de la librairie BootStrap cf : http://getbootstrap.com/ -->
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
		<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
		<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
		
		<title>Jeu d'entreprise</title>
	</head>
	<body>
		<nav class="navbar navbar-inverse" role="navigation">
	      <div class="container-fluid">
	        <!-- Brand and toggle get grouped for better mobile display -->
	        <div class="navbar-header">
	          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-9">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	          <a class="navbar-brand" href="Application?action=home">PlayBusiness</a>
	        </div>
	
	        <!-- Collect the nav links, forms, and other content for toggling -->
	        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-9">
	          <ul class="nav navbar-nav">
	            <li>
	            	<a href="Application?action=home" class="col-xs-12">
	            		<span class="glyphicon glyphicon-dashboard"></span> Home
	            	</a>
	            </li>
	            <li>
	            	<a href="Parties?action=index" class="col-xs-12">
	            		<span class="glyphicon glyphicon-list"></span> Liste de Partie
	            	</a>
	            </li>
	            <li>
	            	<a href="Parties?action=historic" class="col-xs-12">
	            		<span class="glyphicon glyphicon-backward"></span> Mon historique
	            	</a>
	            </li>
	            <li>
	            	<a href="Utilisateurs?action=edit" class="col-xs-12">
	            		<span class="glyphicon glyphicon-wrench"></span> Mes paramètres
	            	</a>
	            </li>
	            <li>
	            	<a href="Application?action=disconnect" class="col-xs-12">
	            		<span class="glyphicon glyphicon-off"></span> Déconnexion
	            	</a>
	            </li>
	          </ul>
	        </div><!-- /.navbar-collapse -->
	      </div><!-- /.container-fluid -->
	    </nav>
	    
        <div class="container">