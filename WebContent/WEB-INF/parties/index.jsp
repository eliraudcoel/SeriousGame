<%@page import="Modeles.Participation"%>
<%@ page import="Modeles.Tour" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="Modeles.Partie" %>
<%@ page import="Modeles.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>

<!-- Insertion Header -->
<%@ include file="../header_global.jsp"  %>

<div class="row">
	<h2 class="col-xs-12">La liste des parties en cours :</h2>
</div>

<%
	String lien = "Parties?action=show&nb=";
	String new_lien = "";
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
					new_lien = lien + partie.getId_partie();
					Tour tour = Tour.last_tour_of_partie(partie);
					int min = Integer.parseInt(tour.getNum_tour());
					int max = Integer.parseInt(partie.getDuree());
					float div = min / (float) max;
					float pourcentage = (div * 100);
			%>
				<tr>
				   <td><a href="<%= new_lien %>"><%= partie.getNom_partie() %></a></td>
				   <td><%= partie.getDate_debut() %></td>
				   <td>
				   	<div class="progress">
					  <div class="progress-bar" role="progressbar" aria-valuenow="<%= tour.getNum_tour() %>" aria-valuemin="0" 
					  aria-valuemax="<%= partie.getDuree() %>" style="width: <%= pourcentage %>%;">
					    <%= tour.getNum_tour()+ "/" +partie.getDuree() %>
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
				<th>Nombre de joueur</th>
				<th>Actions</th>
			</tr>
		</thead>
		<tbody>
			<%
				Utilisateur user = (Utilisateur) session.getAttribute("user");
				ArrayList<Partie> not_started_parties = (ArrayList<Partie>) request.getAttribute("not_started_parties");
				
				for(Partie partie : not_started_parties) {
			%>
				<tr>
				   <td><%= partie.getNom_partie() %></td>
				   <td><%= partie.getDate_debut() %></td>
				   <td><%= partie.nombre_joueurs() %></td>
				   <td>
				   	<form action="Parties" method="post" role="form" class="form-horizontal col-xs-12">
						<input type="hidden" name="type_post" value="participer" />
						<input type="hidden" name="user_id" value="<%= user.getId() %>" />
						<input type="hidden" name="partie_id" value="<%= partie.getId_partie()%>" />
						<% if(Partie.get_participe(user, partie) == true) {%>
							<span class="col-xs-12">Vous y participez!</span>
						<% } else { %>
							<button type="submit" class="btn col-xs-12"> Jouer ! </button>
						<% } %>
				   	</form>
				   </td>
				</tr>
			<%}%>
		</tbody>
	</table>
</div>

<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>