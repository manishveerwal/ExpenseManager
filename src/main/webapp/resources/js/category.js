function expand(id) {
	$("#"+id).show();
	$("#"+id+"A").remove();
	$("#"+id+"TD").append("<a id=\""+id+"A\" href=\"#!\" onclick=\"collapse("+id+");\"><img alt=\"\" src=\"/ExpenseManager/resources/img/expand_icon.png\"></a>");
}

function collapse(id) {
	$("#"+id).hide();
	$("#"+id+"A").remove();
	$("#"+id+"TD").append("<a id=\""+id+"A\" href=\"#!\" onclick=\"expand("+id+");\"><img alt=\"\" src=\"/ExpenseManager/resources/img/expand_icon.png\"></a>");
};

$(document).ready(function() {
	$("#categoryMenu").hover(function() {
		$(".categorySubLink").show();
	}, function() {
		$(".categorySubLink").hide();
	});
});
