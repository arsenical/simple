$(window).on('load', function() {
	$("#predefined").change(function() {
		var option = $("#predefined option:selected").val();
		if (option == "own") {
			enableControlsForCustomPizza();
		} else if (option == "americana") {
			$(".ingredient").prop("checked", false);
			$("#ingr-bacon").prop("checked", true);
			$("#ingr-ham").prop("checked", true);
			$("#ingr-mozzarella").prop("checked", true);
			$("#ingr-pepperoni").prop("checked", true);
			disableControlsForCustomPizza();
		} else if (option == "carbonara") {
			$(".ingredient").prop("checked", false);
			$("#ingr-bacon").prop("checked", true);
			$("#ingr-ham").prop("checked", true);
			$("#ingr-onion").prop("checked", true);
			$("#ingr-mozzarella").prop("checked", true);
			$("#ingr-mashrooms").prop("checked", true);
			$("#ingr-oregano").prop("checked", true);
			disableControlsForCustomPizza();
		} else if (option == "veggie") {
			$(".ingredient").prop("checked", false);
			$("#ingr-onion").prop("checked", true);
			$("#ingr-mozzarella").prop("checked", true);
			$("#ingr-pepper").prop("checked", true);
			$("#ingr-mashrooms").prop("checked", true);
			$("#ingr-tomatoes").prop("checked", true);
			$("#ingr-olives").prop("checked", true);
			disableControlsForCustomPizza();
		}
	});

	function disableControlsForCustomPizza() {
		$(".ingredient").prop("disabled", true);
	}

	function enableControlsForCustomPizza() {
		$(".ingredient").prop("disabled", false);
	}

	$("#btn-order").click(function() {
		window.setTimeout(function() {
			var result = "<span class='success'>Your order has been successfully processed.</span><br><br>Order details:<br>Ingredients: ";
			$(".ingredient:checked").each(function(i) {
				if (i > 0) {
					result += ", ";
				}
				result += $(this).attr("title");
			});
			result += ".<br>Size: " + $("#size option:selected").text() + ".<br>Cheeze: " + $("input[name='cheeze']:checked").val() + ".";
			$("#result").html(result);
		}, 2000);
	});
});
