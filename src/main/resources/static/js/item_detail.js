$(function() {
	const PRICE_LIST = {
		pizza: pizzaPriceList,
		toppings: {
			M: 200, L: 300
		}
	};

	const calcPrice = () => {
		const size = $("input[name=size]:checked").val();
		const pizza_price = PRICE_LIST.pizza[size];

		const topping_count = $("#toppings input[type=checkbox]:checked").length;
		const topping_price = PRICE_LIST.toppings[size] * topping_count;

		const pizza_count = parseInt($("select[name=quantity]").val());

		return (pizza_price + topping_price) * pizza_count;
	}

	const showCalculatedPrice = () => {
		const price = calcPrice();
		$("#totalPrice").text(price.toLocaleString());
	}

	$("input[type=radio]").on("click", showCalculatedPrice);
	$("input[type=checkbox]").on("click", showCalculatedPrice);
	$("select").on("click", showCalculatedPrice);

	showCalculatedPrice();
});
