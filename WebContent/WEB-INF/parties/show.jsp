<%@page import="Modeles.Produit"%>
<%@page import="Modeles.Utilisateur"%>
<%@page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Tour"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>

	<%
		Partie partie = (Partie) request.getAttribute("partie");
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		Entreprise entreprise = user.getEntreprise();
		Tour tour = null;
		
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
			<%= tour.getRegle() %>
		</p>
	</div>
	<% } %>
	
	<div class="row">
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Numero de tour</th>
					<th>Capital Restant</th>
				</tr>
			</thead>
			<tbody>
				<tr>
				   <td>1</td>
				   <td>20 000 &euro;</td>
				</tr>
				<tr class="danger">
				   <td>2</td>
				   <td>18 000 &euro;</td>
				</tr>
				<tr class="success">
				   <td>3</td>
				   <td>25 000 &euro;</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	
	<div class="row">
		<table class="table table-hover products">
			<thead>
				<tr>
					<th>Produit</th>
					<th>Quantité en stock</th>
					<th>Prix d'achat &euro;</th>
					<th>Quantité d'achat</th>
					<th>Prix de vente &euro;</th>
					<th>Quantité de vente</th>
				</tr>
			</thead>
			<tbody>
			<%
			ArrayList<Produit> produits = (ArrayList<Produit>) entreprise.getProduits();
			for(Produit produit : produits) {
			%>
				<tr class="<%= produit.getNom_produit() %>">
				   <td><%= produit.getNom_produit() %></td>
				   <td><%= produit.getStock() %></td>
				   <td><input type="number" value="0" name="<%= produit.getNom_produit() %>_prix_achat" class="prix_achat" /></td>
				   <td><input type="number" value="0" name="<%= produit.getNom_produit() %>_qte_achat" class="qte_achat" /></td>
				   <td><input type="number" value="0" name="<%= produit.getNom_produit() %>_prix_vente" class="prix_vente" /></td>
				   <td><input type="number" value="0" name="<%= produit.getNom_produit() %>_qte_vente" class="qte_vente" /></td>
				</tr>
			<% } %>
			</tbody>
		</table>
	</div>
	
	<div class="jumbotron row">
	  <h3>Récapitulatif</h3>
	  <p class="recap"></p>  
	</div>
	
	<form action="Produits" method="post" role="form" class="form-horizontal col-xs-12 form_products">
		<input type="hidden" name="user_id" value="${user.getId()}" />
		<button type="submit" class="btn btn-success col-xs-12 sign_in_button">Validez!</button>
	</form>

<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>