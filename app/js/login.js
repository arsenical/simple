$(window).on('load', function() {
	$("#login").keyup(function() {
		checkCredentials();
	});

	$("#pswd").keyup(function() {
		checkCredentials();
	});

	function checkCredentials() {
		if ($("#login").val() === "simple" && $("#pswd").val() === "$1mple") {
			$("#btn-login").prop("disabled", false);
		} else {
			$("#btn-login").prop("disabled", true);
		}
	}
	
	$("#btn-login").click(function() {
		window.location = "pizza.html";
	})

});
