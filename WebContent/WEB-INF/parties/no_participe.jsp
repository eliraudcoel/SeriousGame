<%@page import="Modeles.Associer"%>
<%@ page import="Modeles.Utilisateur"%>
<%@ page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Tour"%>
<%@ page import="java.util.*"%>

<div class="row">
	<table class="table table-hover">
		<thead>
			<tr>
				<th>tour</th>
					<% 
					for(Utilisateur partie_user : partie_utilisateurs) {
						if(!partie_user.is_admin()) {
						%>
							<th><%= partie_user.getLogin() %></th>
						<% 
						}
					} 
				%>
			</tr>
		</thead>
		<tbody>
			<tr>
			   <td>Départ</td>
			   <td><%= partie.getCapital_depart() %> &euro;</td>
			</tr>
			<tr>
				<% 
					for(Tour partie_tour : tours) {
						if(!partie_tour.getNum_tour().equals(tour.getNum_tour())) {
					%>
					<td><%= partie_tour.getNum_tour() %></td>
					<% 
						for(Utilisateur partie_user : partie_utilisateurs) {
							if(!partie_user.is_admin()) {
									Entreprise user_ent = partie_user.getEntreprise();
									Associer assoc = Associer.find_by_entreprise(user_ent.getId_entreprise(), partie_tour.getId_tour());
								%>
								<td><%= assoc.getCapital_actuel() %>&euro;</td>
								<% 
							}			
						}
						}
					}
				%>
			</tr>
		</tbody>
	</table>
</div>