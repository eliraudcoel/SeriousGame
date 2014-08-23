$(document).ready(function() {
	
	function count_totaux(tr) {
		prix_a = parseFloat($(tr).find(".prix_achat").val());
		qte_a = parseFloat($(tr).find(".qte_achat").val());
		total_a = parseFloat(prix_a * qte_a);
		
		prix_v = parseFloat($(tr).find(".prix_vente").val());
		qte_v = parseFloat($(tr).find(".qte_vente").val());
		total_v = parseFloat(prix_v * qte_v);
		
		return [total_a, total_v];
	}
	
	function ajout_inputs(product_name, total_a, total_v) {
		var achat_name = product_name+"_total_achat";
		var vente_name = product_name+"_total_vente";
		
		if($("."+achat_name).length) {
			$("."+achat_name).val(total_a);
			$("."+vente_name).val(total_v);
		} else {
			$(".form_products").prepend("<input type='hidden' class='"+achat_name+"' name='"+achat_name+"' value='"+total_a+"' />");
			$(".form_products").prepend("<input type='hidden' class='"+vente_name+"' name='"+vente_name+"' value='"+total_v+"' />");
		}
	}
	
	$(".prix_achat, .qte_achat, .prix_vente, .qte_vente").on('change', function() {
		trs = $("table.products tbody").find("tr");
		var message = "";
		
		$(trs).each(function() {
			var totaux = count_totaux($(this));
			var total_a = totaux[0];
			var total_v = totaux[1];
			message += $(this).attr("class")+" : achat - "+total_a+" € / vente - "+total_v+" €";
			ajout_inputs($(this).attr("class"), total_a, total_v);
		});
		
		$(".recap").text(message);
	});
});