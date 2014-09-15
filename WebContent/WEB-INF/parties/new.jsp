<!-- Insertion Header -->
<%@ include file="../header_global.jsp"  %>

	<form action="Parties" method="post" role="form" class="form-horizontal">
		<input type="hidden" name="user_id" value="${user.getId()}" />
		<input type="hidden" name="type_post" value="ajouter" />
		<div class="input-group col-xs-12">
			<label>Nom de la partie</label>
			<input placeholder="Nom de la partie" name="nom_partie" class="form-control"></input>
		</div>
		<div class="input-group col-xs-12">
			<label>Durée de la partie</label>
			<input type="number" value="0" name="duree" class="form-control"></input>
		</div>		
		<div class="input-group col-xs-12">
			<div class="col-xs-12"><label>Date de début</label></div>
			<div class="col-xs-6"><input type="date" name="date_deb" class="form-control"></input></div>
			<div class="col-xs-6"><input type="time" name="heure_deb" class="form-control"></input></div>
		</div>
		<div class="input-group col-xs-12">
			<label>Capital de départ</label>
			<input type="number" value="0" name="capital_dep" class="form-control"></input>
		</div>
		<div class="input-group col-xs-12">
			<label>Coût du salaire</label>
			<input type="number" value="0" name="cout_salaire" class="form-control"></input>
		</div>
		<div class="input-group col-xs-12">
			<label>Coût charge d'exploitation</label>
			<input type="number" value="0" name="cout_char_exp" class="form-control"></input>
		</div>
		<div class="input-group col-xs-12">
			<label>Coût du loyer</label>
			<input type="number" value="0" name="cout_loyer" class="form-control"></input>
		</div>
		<div class="input-group col-xs-12">
			<button type="submit" class="btn btn-success col-xs-12 sign_in_button">Ajoutez!</button>
		</div>
	</form>

<!-- Insertion Footer -->
<%@ include file="../footer.jsp"  %>