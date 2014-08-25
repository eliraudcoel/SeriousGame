$(document).ready(function() {
	
	function count_totaux(tr) {
		prix_a = parseFloat($(tr).find(".prix_achat").val());
		qte_a = parseFloat($(tr).find(".qte_achat").val());
		total_a = parseFloat(prix_a * qte_a);
		
		prix_v = parseFloat($(tr).find(".prix_vente").val());
		qte_v = parseFloat($(tr).find(".qte_vente").val());
		total_v = parseFloat(prix_v * qte_v);
		
		ajout_inputs(tr, prix_a, qte_a, prix_v, qte_v);
		
		return [total_a, total_v];
	}
	
	function ajout_inputs(product, prix_a, qte_a, prix_v, qte_v) {
		var product_name = $(product).attr("class");
		var prix_a_name = product_name+"_prix_achat";
		var qte_a_name = product_name+"_qte_achat";
		var prix_v_name = product_name+"_prix_vente";
		var qte_v_name = product_name+"_qte_vente";
		
		if($("."+prix_a_name).length) {
			$("."+prix_a_name).val(prix_a);
			$("."+qte_a_name).val(qte_a);
			$("."+prix_v_name).val(prix_v);
			$("."+qte_v_name).val(qte_v);
		} else {
			$(".form_products").prepend("<input type='hidden' class='"+prix_a_name+"' name='"+prix_a_name+"' value='"+prix_a+"' />");
			$(".form_products").prepend("<input type='hidden' class='"+qte_a_name+"' name='"+qte_a_name+"' value='"+qte_a+"' />");
			$(".form_products").prepend("<input type='hidden' class='"+prix_v_name+"' name='"+prix_v_name+"' value='"+prix_v+"' />");
			$(".form_products").prepend("<input type='hidden' class='"+qte_v_name+"' name='"+qte_v_name+"' value='"+qte_v+"' />");
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
		});
		
		$(".recap").text(message);
	});
});