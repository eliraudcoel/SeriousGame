<%@page import="Modeles.Associer"%>
<%@page import="Modeles.Produit"%>
<%@page import="Modeles.Utilisateur"%>
<%@page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Tour"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header_global.jsp"  %>

	<%
		Partie partie = (Partie) request.getAttribute("partie");
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		Entreprise entreprise = user.getEntreprise();
		List<Tour> tours = Tour.find_by_partie(partie.getId_partie());
	%>
	<div class="row">
		<h2 class="col-xs-12">Partie <%= partie.getNom_partie() %></h2>
	</div>
	
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Numero de tour</th>
					<th>Capital Restant</th>
				</tr>
			</thead>
			<tbody>
			<%
				for(Tour tour : tours) {
				
				Associer associer = Associer.find_by_entreprise(entreprise.getId_entreprise(), tour.getId_tour());
			 %>
				<tr>
				   <td><%= tour.getNum_tour() %></td>
				   <td><%= associer.getCapital_actuel() %>&euro;</td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>