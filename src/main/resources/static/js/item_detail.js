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

		const pizza_count = parseInt($("input[name=quantity]").val()) || 0;

		return (pizza_price + topping_price) * pizza_count;
	}

	const showCalculatedPrice = () => {
		const price = calcPrice();
		$("#totalPrice").text(price.toLocaleString());
	}

	$("input").on("change", showCalculatedPrice);
	$("input[type=number]").on("keyup", showCalculatedPrice);

	showCalculatedPrice();

	const getQuantity = () => $("#quantity").val();
	const updateQuantity = (i) => {
		$("#quantity").val(i);
	};

	$("#quantityPlusButton").on("click", () => {
		let quantity = getQuantity();
		quantity++;
		updateQuantity(quantity);
		showCalculatedPrice();
	});
	$("#quantityMinusButton").on("click", () => {
		let quantity = getQuantity();
		// 0以下にはしない
		if (quantity <= 1) return;
		quantity--;
		updateQuantity(quantity);
		showCalculatedPrice();
	});
});
