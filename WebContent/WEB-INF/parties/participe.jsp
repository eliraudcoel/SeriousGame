<%@page import="Modeles.Utilisateur"%>
<%@page import="Modeles.Associer"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Tour"%>
<%@ page import="java.util.ArrayList"%>

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
			   <td>Départ</td>
			   <td><%= partie.getCapital_depart() %> &euro;</td>
			</tr>
			<%
				Associer associer = null;
				for(Tour un_tour : tours) {
					if(!un_tour.getNum_tour().equals(tour.getNum_tour())) {
						associer = Associer.find_by_entreprise(entreprise.getId_entreprise(), un_tour.getId_tour());
				 		%>
							<tr>
							   <td><%= un_tour.getNum_tour() %></td>
							   <td><%= associer.getCapital_actuel() %>&euro;</td>
							</tr>
						<%
					}
			 	}
			%>
		</tbody>
	</table>
</div>


<% if(partie.user_can_play(user)) { %>
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
		<input type="hidden" name="partie_id" value="${partie.getId_partie()}" />
		<button type="submit" class="btn btn-success col-xs-12 sign_in_button">Validez!</button>
	</form>
<% } else { %>
	<span>En attente de l'administrateur</span>
<% } %>