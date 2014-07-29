<!-- Insertion Header -->
<%@ include file="header.jsp"  %>

	<!-- Texte de bienvenue -->
	<h2> Formulaire de connexion </h2>
	
	<% if(request.getAttribute("message") != null) { %>
	<div class="row alert alert-danger alert-dismissible" role="alert">
		<p class="col-xs-12"><%= request.getAttribute("message") %></p>
	</div>
	<% } %>
	
	<div class="row">
		<form action="Connection" method="post" role="form" class="form-horizontal col-xs-12">
			<div class="form-group">
				<input placeholder="Login" name="login" class="form-control"></input>
			</div>
			<div class="form-group">
				<input type="password" placeholder="Mot de passe" name="mdp" class="form-control"></input>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-success col-xs-12">Se connecter!</button>
			</div>
		</form>
	</div>
	
	
<!-- Insertion Footer -->
<%@ include file="footer.jsp"  %>