<%@page import="Modeles.Produit"%>
<%@page import="Modeles.Utilisateur"%>
<%@page import="Modeles.Entreprise"%>
<%@ page import="Modeles.Partie"%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>

<!-- Insertion Header -->
<%@ include file="../header.jsp"  %>

	<%
		Partie partie = (Partie) request.getAttribute("partie");
		Utilisateur user = (Utilisateur) session.getAttribute("user");
		Entreprise entreprise = user.getEntreprise();
	%>
	<div class="row">
		<h2 class="col-xs-12">Partie <%= partie.getNom_partie() %></h2>
	</div>
	
	<div class="row">
		<h2 class="col-xs-12">
			<span class="glyphicon glyphicon-pushpin"></span> Règles de la partie :
		</h2>
		<p class="col-xs-12">
			Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt 
			ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation 
			ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor 
			in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at 
			vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore 
			te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming 
			id quod mazim placerat facer possim assum. Typi non habent claritatem insitam; est usus legentis in iis qui 
			facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius.
		</p>
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