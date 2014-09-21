<%@page import="jdk.nashorn.internal.ir.LiteralNode.ArrayLiteralNode"%>
<%@page import="Modeles.Evenement"%>
<%@page import="Modeles.Entreprise"%>
<%@page import="Modeles.Utilisateur"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="Modeles.Produit"%>
<%@ page import="java.util.ArrayList"%>

<!--  Indiquer le nombre du tour courant -->

<div class="row">
	<h2 class="col-xs-12">Tour <%= tour.getNum_tour() %></h2>
</div>

<% if(partie.admin_can_play()) { %>
	<form action="Produits" method="post" role="form" class="form-horizontal col-xs-12 form_products">
		<div class="row">
			<table class="table table-hover products">
				<thead>
					<tr>
						<th>Joueur</th>
						<th>Produit</th>
						<th>Prix d'achat &euro;</th>
						<th>Prix de vente &euro;</th>
						<th>Perte de stock</th>
					</tr>
				</thead>
				<tbody>
				<%			
					for(Utilisateur user_partie : partie_utilisateurs) {
						Entreprise user_entreprise = user_partie.getEntreprise();
						ArrayList<Produit> partie_produits = (ArrayList<Produit>) user_entreprise.getProduits();
						ArrayList<Evenement> user_evenements = (ArrayList<Evenement>) Evenement.find_by_utilisateur(user_partie.getId(), tour.getId_tour());
						ArrayList<String> id_produits = new ArrayList<String>();
						for(Evenement user_evenement : user_evenements) {
							id_produits.add(user_evenement.getId_produit());
						}
						for(Produit partie_produit : partie_produits) {
							if(id_produits.contains(partie_produit.getId_produit())) {
				%>
					<tr>
						<td><%= user_partie.getLogin() %></td>
						<td><%= partie_produit.getNom_produit() %></td>
						<td>
							<input type="text" name="<%= user_partie.getLogin() %>_<%= partie_produit.getNom_produit() %>_prix_achat" value ="0" />
						</td>
						<td>
							<input type="text" name="<%= user_partie.getLogin() %>_<%= partie_produit.getNom_produit() %>_prix_vente" value ="0" />
						</td>
						<td>
							<input type="text"	name="<%= user_partie.getLogin() %>_<%= partie_produit.getNom_produit() %>_perte" value ="0" />
						</td>
					</tr>
				<%			}
						} 
					}
				%>
				</tbody>
			</table>
		</div>
		<input type="text" name="regle" placeholder="Entrez une regle" />
		<input type="hidden" name="user_id" value="${user.getId()}" />
		<input type="hidden" name="partie_id" value="${partie.getId_partie()}" />
		<button type="submit" class="btn btn-success col-xs-12 sign_in_button">Validez!</button>
	</form>
<% } else { %>
	<span>En attente des joueurs</span>
<% } %>