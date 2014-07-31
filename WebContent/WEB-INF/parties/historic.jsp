<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Utilisateur"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>

<div class="row">
		<h2 class="col-xs-12">La liste des parties antérieures :</h2>
	</div>
	
	<%
		String lien = "Parties?action=show&nb=";
		ArrayList<Partie> finished_parties = (ArrayList<Partie>) request.getAttribute("finished_parties");
		
		if(finished_parties.size() > 0) {
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
					for(Partie partie : finished_parties) {
						lien = lien + partie.getId_partie();
				%>
					<tr>
					   <td><a href="<%= lien %>"><%= partie.getNom_partie() %></a></td>
					   <td><%= partie.getDate_debut() %></td>
					   <td>
					   	<div class="progress">
						  <div class="progress-bar" role="progressbar" aria-valuenow="<%= partie.getDuree() %>" aria-valuemin="0" aria-valuemax="<%= partie.getDuree() %>" style="width: 100%;">
						    <%= partie.getDuree() %>/<%= partie.getDuree() %>
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
			<p class="col-xs-12"> Aucune partie antérieur</p>
			<%
		}
	%>
	
<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>