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
					<th>Nombre de tour</th>
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
					   <td><%= partie.getDuree() %></td>
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
		<form action="Parties" method="post" role="form" class="form-horizontal col-xs-12">
			<%
				Utilisateur user = (Utilisateur) session.getAttribute("user");
			%>
		<input type="hidden" name="user_id" value="<%= user.getId() %>" />
		<div class="form-group">
				<select class="form-control" name="partie_id">
					<%
						ArrayList<Partie> not_started_parties = (ArrayList<Partie>) request.getAttribute("not_started_games");
						
						for(Partie partie : not_started_parties) {
							String en_cours = "";
					%>
					  <option value="<%= partie.getId_partie()%>"><%= partie.getNom_partie() %></option>
					<%}%>
				</select>
			</div>
			<div class="form-group">
			 <button type="submit" class="btn btn-success col-xs-12">Rejoindre!</button>
			</div>
	</div>
<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>