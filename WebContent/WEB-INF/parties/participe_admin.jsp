<%@page import="Modeles.Entreprise"%>
<%@page import="Modeles.Utilisateur"%>
<%@ page import="Modeles.Partie"%>
<%@ page import="java.util.ArrayList"%>

<!--  Indiquer le nombre du tour courant -->

<div class="row">
	<table class="table table-hover products">
		<thead>
			<tr>
				<th>Joueur</th>
				<th>Produit</th>
				<th>Prix d'achat &euro;</th>
				<th>Quantité d'achat</th> <!-- peux pas la changer -->
				<th>Prix de vente &euro;</th>
				<th>Quantité de vente</th>
				<th>Perte de stock</th>
			</tr>
		</thead>
		<tbody>
		<%
			// Boucle sur les utilisateurs ok
			// Boucle sur leurs produits ok
			// mettre les evenements
			
			// POST : 
			// si prix achat != ===> evenemnent achat avec le nouveaux prix
			// si prix/qte vente != ===> idem que achat + qte à changer
			
			// Le capital est calculé :
			// Capital actuel
			// - prix achat / qte (admin)
			// + prix achat / qte (admin)
			// - charges
			
			//for(Utilisateur user : partie_utilisateurs) {
			
			//Entreprise user_entreprise = user.getEntreprise();
			//ArrayList<Produit> produits = (ArrayList<Produit>) user_entreprise.getProduits();
			//for(Produit produit : produits) {
		%>
			<tr>
			   <td></td>
			</tr>
		<% //} %>
		</tbody>
	</table>
</div>

<form action="Produits" method="post" role="form" class="form-horizontal col-xs-12 form_products">
	<input type="hidden" name="user_id" value="${user.getId()}" />
	<button type="submit" class="btn btn-success col-xs-12 sign_in_button">Validez!</button>
</form>