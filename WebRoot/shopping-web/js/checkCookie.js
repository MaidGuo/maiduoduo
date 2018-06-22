function getCurrentTime(){

	    var mydate = new Date();
	    var now = "" + mydate.getFullYear() + "年";
	    now += (mydate.getMonth()+1) + "月";
	    now += mydate.getDate() + "日";
	    return now;
   }


function setCookie(c_name,value)
{
	var exdate=new Date();
//	exdate.setDate(exdate.getDate()+expiredays);

	document.cookie=c_name+ "=" +escape(value);

};

function getCookie(c_name)
{
	if (document.cookie.length>0)
	  {
	  	c_start=document.cookie.indexOf(c_name + "=");

		  	if (c_start!=-1)
		    { 
			    c_start=c_start + c_name.length+1 ;
			    c_end=document.cookie.indexOf(";",c_start);

			    if (c_end==-1) c_end=document.cookie.length;

			    return unescape(document.cookie.substring(c_start,c_end));
		    } 
	  }
	return "";
}

function checkCookie(el,userid)
{
	//userid=getCookie('userid');
	
	  var curWwwPath=window.document.location.href;  
      //获取主机地址之后的目录，如： /uimcardprj/share/meun.jsp  
      var pathName=window.document.location.pathname;  
      var pos=curWwwPath.indexOf(pathName);  
      //获取主机地址，如： http://localhost:8083  
      var localhostPaht=curWwwPath.substring(0,pos);  
      //获取带"/"的项目名，如：/uimcardprj  
      var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
	if (userid!=0 || userid!="")
	  {
		
	  	console.log($(el).text());
	  	if($(el).text() == "我的买多多"){
			location.href = projectName+"/servlet/UserInfoGetAction?id="+userid;
		}else if($(el).text() == "购物车"){
			location.href = projectName+"/ShoppingCartAction?flag=show";
		}else if($(el).text() == "我的订单"){
			location.href=projectName+"/servlet/OrderAction";
		}
	  }
	else 
	  {
		swal({
			title:"你还没有登录，请登录后再查看",
			text:"正在前往登录页面",
			type:"warning",
			showConfirmButton: true
			},function(){
				
			        location.href = projectName+"/shopping-web/jsp/login.jsp";

		});

	  }
}

$(".btn-search-product").unbind("click");
$(".btn-search-product").bind("click",function(){

	if($("input[name='search-goods']").val() == "" || $("input[name='search-goods']").val() == null){
		swal({
				title:"请输入想要的商品 ",
				type:"warning",
				timer:2000
			});

	}else{
		var spname = $("input[name='search-goods']").val();

		location.href = "searchProduct.html?name="+spname;
	}
})