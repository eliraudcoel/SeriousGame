<%@page import="java.util.Date"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>
	<div class="row">
		<h2 class="col-xs-12">La liste des parties en cours :</h2>
	</div>
	
	<%
		String lien = "Parties?action=show&nb=";
		ArrayList<Partie> in_processing_parties = (ArrayList<Partie>) request.getAttribute("in_processing_parties");
		
		if(in_processing_parties.size() > 0) {
	%>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Nom de la partie</th>
					<th>Date de début</th>
					<th>Avancement</th>
				</tr>
			</thead>
			<tbody>
				<%
					for(Partie partie : in_processing_parties) {
						lien = lien + partie.getId_partie();
				%>
					<tr>
					   <td><a href="<%= lien %>"><%= partie.getNom_partie() %></a></td>
					   <td><%= partie.getDate_debut() %></td>
					   <td>
					   	<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="<%= partie.getDuree() %>" style="width: 80%;">
						    3/<%= partie.getDuree() %>
						  </div>
						</div>
					   </td>
					</tr>
				<%}%>
			</tbody>
		</table>
	</div>
	<%
		} else {
			%>
			<p class="col-xs-12"> Aucune partie en cours</p>
			<%
		}
	%>
	
	<div class="row">
		<h2 class="col-xs-12"> Rejoindre une partie :</h2>
	</div>
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Nom de la partie</th>
					<th>Date de début</th>
					<th>Avancement</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
					Utilisateur user = (Utilisateur) session.getAttribute("user");
					ArrayList<Partie> not_started_parties = (ArrayList<Partie>) request.getAttribute("not_started_games");
					
					for(Partie partie : not_started_parties) {
				%>
					<tr>
					   <td><a href="<%= lien %>"><%= partie.getNom_partie() %></a></td>
					   <td><%= partie.getDate_debut() %></td>
					   <td>
					   	<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="2" aria-valuemin="0" aria-valuemax="<%= partie.getDuree() %>" style="width: 60%;">
						    2/<%= partie.getDuree() %>
						  </div>
						</div>
					   </td>
					   <td>
					   	<form action="Parties" method="post" role="form" class="form-horizontal col-xs-12">
							<input type="hidden" name="user_id" value="<%= user.getId() %>" />
							<input type="hidden" name="partie_id" value="<%= partie.getId_partie()%>" />
							<button type="submit" class="btn col-xs-12"> Jouer ! </button>
					   	</form>
					   </td>
					</tr>
				<%}%>
			</tbody>
		</table>
	</div>
	
	<!--
	<div class="row">
		<form action="Parties" method="post" role="form" class="form-horizontal col-xs-12">
			<#
				Utilisateur user = (Utilisateur) session.getAttribute("user");
			%>
		<input type="hidden" name="user_id" value="<# user.getId() %>" />
		<div class="form-group">
				<select class="form-control" name="partie_id">
					<#
						ArrayList<Partie> not_started_parties = (ArrayList<Partie>) request.getAttribute("not_started_games");
						
						for(Partie partie : not_started_parties) {
							String en_cours = "";
					%>
					  <option value="<# partie.getId_partie()%>"><# partie.getNom_partie() %></option>
					<#}%>
				</select>
			</div>
			<div class="form-group">
			 <button type="submit" class="btn btn-success col-xs-12">Rejoindre!</button>
			</div>
	</div>
	-->
<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>