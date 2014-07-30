<%@ page import="Modeles.Utilisateur"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>
	
	<% if(request.getAttribute("message_email") != null || request.getAttribute("message_mdp") != null) { %>
	<div class="row alert alert-danger alert-dismissible" role="alert">
		<p class="col-xs-12"><%= request.getAttribute("message_email") %></p>
		<p class="col-xs-12"><%= request.getAttribute("message_mdp") %></p>
	</div>
	<% } %>
	
	<div class="row">
		<h2 class="col-xs-12">Votre Profil</h2>
	</div>
	
	<% Utilisateur user = (Utilisateur) session.getAttribute("user"); %>
	
	<div class="row">
		<form action="Utilisateurs" method="post" role="form" class="form-horizontal col-xs-12">
			<input type="hidden" name="user_id" value="<%= user.getId() %>" />
			<div class="form-group">
				<label class="col-xs-12">Email :</label>
				<input placeholder="Email" name="email" class="form-control" value="<%= user.getEmail() %>" />
			</div>
			<div class="form-group">
				<label class="col-xs-12">Login :</label>
				<input placeholder="Login" name="login" class="form-control" value="<%= user.getLogin() %>" />
			</div>
			<div class="form-group">
				<label class="col-xs-12">Changement de mot de passe :</label>
				<input type="password" placeholder="Mot de passe" name="mdp" class="form-control"></input>
				<input type="password" placeholder="Mot de passe (confirmation)" name="mdp_confirm" class="form-control" />
			</div>
			<div class="form-group">
			 <button type="submit" class="btn btn-success col-xs-12">Modifier</button>
			</div>
		</form>
	</div>
	
<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>