<%@page import="Modeles.Produit"%>
<%@page import="Modeles.Utilisateur"%>
<%@page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Tour"%>
<%@page import="Modeles.Associer"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header_global.jsp"  %>

	<%
		Partie partie = (Partie) request.getAttribute("partie");
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		Entreprise entreprise = user.getEntreprise();
		Tour tour = null;
		ArrayList<Utilisateur> partie_utilisateurs = (ArrayList<Utilisateur>) partie.get_all_utilisateurs();
		ArrayList<Tour> tours = (ArrayList<Tour>) Tour.find_by_partie(partie.getId_partie());
		
		if(partie.is_processing()){
			tour = Tour.last_tour_of_partie(partie);
		}
	%>
	<div class="row">
		<h2 class="col-xs-12">Partie <%= partie.getNom_partie() %></h2>
	</div>
	
	<% if(tour != null) { %>
		<div class="row">
			<h2 class="col-xs-12">
				<span class="glyphicon glyphicon-pushpin"></span> Règles de la partie :
			</h2>
			<p class="col-xs-12">
			<% if(tour.getRegle() == null) { %>
				Aucune règle pour ce tour.
			<% } else { %>
				<%= tour.getRegle() %>
			<% } %>
			</p>
		</div>
	<% } %>
	
	<% if(!user.is_admin() && partie.is_participe(user)) { %>
		<%@ include file="participe.jsp" %>
	<% } if(user.is_admin() && partie.is_participe(user)) { %>
		<%@ include file="participe_admin.jsp" %>
	<% } if(!partie.is_participe(user)) { %>
		<%@ include file="no_participe.jsp" %>
	<% } %>

<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>