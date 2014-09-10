<%@page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Utilisateur"%>
<%@ page import="Modeles.Entreprise"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>
	
	<% if(request.getAttribute("message_ent") != null && request.getAttribute("message_mdp") != null) { %>
		<div class="row alert alert-danger alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
			<%
			   if(request.getAttribute("message_mdp") != null) {
			%>
			<strong>Attention! </strong><%= request.getAttribute("message_mdp") %>
			<% }
			   if(request.getAttribute("message_ent") != null) {
			%>
			<strong>Attention! </strong><%= request.getAttribute("message_ent") %>
			<% } %>
		</div>
	<% } %>
	
	<div class="row">
		<h2 class="col-xs-12">Votre Profil</h2>
	</div>
	
	<% Utilisateur user = (Utilisateur) session.getAttribute("user"); %>
	
	<div class="row">
		<form action="Utilisateurs" method="post" role="form" class="form-horizontal col-xs-12">
			<input type="hidden" name="user_id" value="${user.getId()}" />
			<div class="form-group">
				<% Entreprise entreprise = user.getEntreprise();%>
				<label class="col-xs-12">Entreprise :</label>
				<input placeholder="Entreprise" name="entreprise" class="form-control" value="<%= entreprise.getNom_entreprise() %>" />
			</div>
			<div class="form-group">
				<label class="col-xs-12">Login :</label>
				<input placeholder="Login" name="login" class="form-control" value="${user.getLogin()}" />
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