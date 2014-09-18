<%@page import="Modeles.Associer"%>
<%@ page import="Modeles.Utilisateur"%>
<%@ page import="java.util.*"%>

<div class="row">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>tour</th>
				<% for(Utilisateur partie_user : partie_utilisateurs) {	%>
					<th><%= partie_user.getLogin() %></th>
				<% } %>
			</tr>
		</thead>
		<tbody>
			<tr>
				<% ArrayList<Tour> partie_tours = (ArrayList<Tour>) Tour.find_by_partie(partie.getId_partie()); %>
				<% for(Tour partie_tour : partie_tours) { %>
					<td><%= partie_tour.getNum_tour() %></td>
				<% } %>
				<% 
					Associer assoc = null;
					for(Utilisateur partie_user : partie_utilisateurs) {
				%>
					<td><%= partie_user.getLogin() %></td>
				<% } %>
			</tr>
		</tbody>
	</table>
</div>