function load(count,id){ 
	if(count=="1"){
	
		swal({
		    title:" 新增评论成功",    
		    text: "",
		    type: "success",    
		    confirmButtonText: "确定" 
		}, function(){
			var curWwwPath=window.document.location.href; 
		      //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp  
		      var pathName=window.document.location.pathname;  
		      var pos=curWwwPath.indexOf(pathName);  
		      //获取主机地址，如： http://localhost:8083  
		      var localhostPaht=curWwwPath.substring(0,pos);  
		      //获取带"/"的项目名，如：/uimcardprj  
		      var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
		 
			window.location.href=projectName+"/servlet/ReviewGetAction?id="+id+"&pageNum=1"; 
		}); 
	}
}	