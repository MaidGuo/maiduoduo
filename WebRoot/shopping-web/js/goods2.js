// JavaScript Document

$(document).ready(function(){

	$(".jqzoom").imagezoom();
	
	$("#thumblist li a").click(function(){
		$(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
		$(".jqzoom").attr('src',$(this).find("img").attr("mid"));
		$(".jqzoom").attr('rel',$(this).find("img").attr("big"));
	});

});

function jian() {
    var i = parseInt(document.getElementById("shang").valueOf().value) - 1;
    if (i <= 1) {
        i = 1;
    }
    document.getElementById("shang").valueOf().value = i;
    document.getElementById("yiqian").value=suan(price1);
    zong();
}
function jia(num) {
	var past = parseInt(document.getElementById("shang").valueOf().value);
	if(past < num){
		var i = past + 1;
	    document.getElementById("shang").valueOf().value = i;
	    document.getElementById("yiqian").value=suan(price1);
	   
	}
    
}
