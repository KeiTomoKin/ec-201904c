$(function() {
	const creditCardValue = "2";
	$("input[name=paymentMethod]").on("change", function() {
		const creditCardForm = $("#creditCardForm");
		console.log(this);
		if (this.value === creditCardValue) { // クレジットカード決済が選択された場合
			creditCardForm.show();
			creditCardForm.find("input,select").prop("disabled", false)
		} else {
			creditCardForm.hide();
			creditCardForm.find("input,select").prop("disabled", true)
		}
	});

	// 現在の状態で更新
	$("input[name=paymentMethod]:checked").trigger("change");
});
