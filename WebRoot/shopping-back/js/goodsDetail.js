/**
 * 商品全部信息
 * 商品增加时判断
 */
//返回
 function returnLast(){
	 window.history.back();  //返回上一页
 }
 
 function check(){
	//不为空
	if(goodsDetailsFrm.gname.value.length ==0 || goodsDetailsFrm.gpric==null || goodsDetailsFrm.gnum==null
			||goodsDetailsFrm.gfC.value.length==0||goodsDetailsFrm.gsC.value.length==0 ||
			goodsDetailsFrm.gtC.value.length==0 ){
		alert("表单内不能为空 ！");
		return false;
	}
	//必须数值
	var reg = /^[0-9]+.?[0-9]*$/;
	if(!reg.test(goodsDetailsFrm.gpric.value) || !reg.test(goodsDetailsFrm.gnum.value)){
		alert("价格，库存量必须为数值 ！");
		return false;
	}
	return true;
 }
 
/*/三级联动
 function changeGoods( )
 {
 	 var goodsList1 = new Array( );
 	goodsList1['美妆/个人护理'] = ['彩妆', '洗护','护肤品'];
    goodsList1['零食/进口食品'] =['饮品','糖果', '干果', '饼干'];
 	goodsList1['大家电 /生活电器'] = ['Oracle','Struts', 'EJB3.0', 'Ajax','Spring','Hibernate'];
    goodsList1['生鲜水果']=['a'];
    
    var goodsList2 = new Array( );
    goodsList2['彩妆']=['口红','class2'];
    goodsList2['SqlServer基础']=['class1','class2'];
    
    goodsList2['Java']=['class5','class6'];
    goodsList2['Ajax']=['class3','class6'];
 	 //获得学期选项的索引号，如第一学期为1，比对应数组索引号多1
 	 var pIndex=document.myform.selTerm.value; 
 	 var newOption1;
 	 document.myform.gfC.options.length=0;
 	 for (var j in goodsList1[pIndex]) 
 	 {
        newOption1=new Option(goodsList1[pIndex][j], goodsList1[pIndex][j]); 
 		 document.myform.selCourse.options.add(newOption1);
     }
 	 //
 	 var pIndex2=document.gsC.selCourse.value; 
 	 var newOption2;
 	 document.myform.selClass.options.length=0;
 	 for (var i in goodsList2[pIndex2]) 
 	 {
        newOption2=new Option(goodsList2[pIndex2][i], goodsList2[pIndex2][i]); 
 		 document.myform.selClass.options.add(newOption2);
     }
 }*/