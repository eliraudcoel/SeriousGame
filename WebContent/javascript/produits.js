$(document).ready(function() {
	
	function count_totaux() {
		prix_a = parseFloat($(".prix_achat").val());
		qte_a = parseFloat($(".qte_achat").val());
		total_a = parseFloat(prix_a * qte_a);
		
		prix_v = parseFloat($(".prix_vente").val());
		qte_v = parseFloat($(".qte_vente").val());
		total_v = parseFloat(prix_v * qte_v);
		
		return [total_a, total_v];
	}
	
	$(".prix_achat, .qte_achat").on('change', function() {
		tr_parent = $(this).parent().parent();
		// A FIXER
		total_a, total_v = count_totaux();
	});
	
	$(".prix_vente, .qte_vente").on('change', function() {
		tr_parent = $(this).parent().parent();
		total_a, total_v = count_totaux();
	});
});