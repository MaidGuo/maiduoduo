/**
 * 
 */
function selectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			obj[i].checked = true;
		}
	}
}
function unselectAll(){
	var obj = document.fom.elements;
	for (var i=0;i<obj.length;i++){
		if (obj[i].name == "delid"){
			if (obj[i].checked==true) obj[i].checked = false;
			else obj[i].checked = true;
		}
	}
}
function on_load(){
	var loadingmsg=document.getElementById("loadingmsg");
	var mainpage=document.getElementById("mainpage");
	loadingmsg.style.display="";
	mainpage.style.display="none";
	
	loadingmsg.style.display="none";
	mainpage.style.display="";
}

//传递选中的商品
function postSelect(){
	var ob = document.getElementsByName("delid");
	var goodsIds = "";//String
	 if (ob != null) {
		  for ( var i = 0; i < ob.length; i++) {
		    if (ob[i].checked == true) {
		    	goodsIds += ob[i].nextSibling.nodeValue + ",";
		   }
		  }
	 }
	 var len = goodsIds.length - 1;
	 goodsIds = goodsIds.substring(0, len);
	document.getElementById("gIDs").value = goodsIds;
	//在此将JS中的v变量的值交给JSP
    document.Isform.submit();
    return goodsIds;
}