package dao;

import java.util.ArrayList;

import domain.FirstClassifyInfo;
import domain.GoodsImgInfo;
import domain.GoodsInfo;
import domain.ManageGoodsInfo;
import domain.SecondClassifyInfo;
import domain.ThridClassifyInfo;

public class ManageGoodsDao {
	//显示Manage
	public ArrayList<ManageGoodsInfo> mAppear(){
		ArrayList<ManageGoodsInfo> manageG = new ArrayList<ManageGoodsInfo>();//存储
		ArrayList<GoodsInfo> goodsAll = new ArrayList<GoodsInfo>();//GoodsDB getAllGoods
		ManageGoodsInfo mgoods = new ManageGoodsInfo();
		GoodsImgDao goodsImgDao = new GoodsImgDao();//pic
		GoodsImgInfo gImgInfo= new GoodsImgInfo();//pic
		
		ClassifyDao classifyDB = new ClassifyDao();
		FirstClassifyInfo fC=new FirstClassifyInfo();//1
		SecondClassifyInfo sC=new SecondClassifyInfo();//2
		ThridClassifyInfo tC=new ThridClassifyInfo();//3
		
		GoodsDao goodsDao=new GoodsDao();
		goodsAll=goodsDao.getAllGoods();
		
		for(int i=0;i<goodsAll.size();i++){
			mgoods = new ManageGoodsInfo();
			mgoods.setmId(goodsAll.get(i).getGoods_id());//商品ID
			
			mgoods.setmName(goodsAll.get(i).getGoods_name());
			gImgInfo=goodsImgDao.getOneGoodsImg(goodsAll.get(i).getGoods_id());
			mgoods.setmPicPath(gImgInfo.getImg_path());//图片路径
	
			tC=classifyDB.getThridClassify(goodsAll.get(i).getT_classify_id());//分类
			sC=classifyDB.getSecondClassify(tC.getS_classify_id());//2
			fC=classifyDB.getFirstClassify(sC.getF_classify_id());//1
			mgoods.setmTCname(tC.getT_Classify_name());
			mgoods.setmSCname(sC.getS_Classify_name());
			mgoods.setmFCname(fC.getF_Classify_name());
			
			manageG.add(mgoods);
		}
		return manageG;
	}
	
	/*
	 * 显示goodsDetails
	 * 显示一个完整goods
	 */
	public GoodsInfo goodsDetails(int id){
		GoodsInfo goods=new GoodsInfo();//存储
		GoodsInfo goodsOne=new GoodsInfo();
		GoodsDao goodsDao=new GoodsDao();
		goodsOne=goodsDao.getOneGoods(id);
		GoodsImgDao imgDB=new GoodsImgDao();
		GoodsImgInfo imgInfo = imgDB.getOneGoodsImg(id);
		//类别
		ClassifyDao classifyDB = new ClassifyDao();
		FirstClassifyInfo fC=new FirstClassifyInfo();//1
		SecondClassifyInfo sC=new SecondClassifyInfo();//2
		ThridClassifyInfo tC=new ThridClassifyInfo();//3
		
		goods.setGoods_id(id);
		goods.setGoods_name(goodsOne.getGoods_name());//名称
		goods.setGoods_price(goodsOne.getGoods_price());//价格
		goods.setGoods_num(goodsOne.getGoods_num());//库存量
		goods.setPurchase_times(goodsOne.getPurchase_times());
		goods.setComment_times(goodsOne.getComment_times());
		goods.setStatus(goodsOne.getStatus());//状态
		goods.setGoods_description(goodsOne.getGoods_description());
		goods.setImg_path(imgInfo.getImg_path());
		
		goods.setT_classify_id(goodsOne.getT_classify_id());//3
		tC=classifyDB.getThridClassify(goodsOne.getT_classify_id());//分类
		sC=classifyDB.getSecondClassify(tC.getS_classify_id());//2
		fC=classifyDB.getFirstClassify(sC.getF_classify_id());//1
		goods.setT_Classify_name(tC.getT_Classify_name());
		goods.setS_Classify_name(sC.getS_Classify_name());
		goods.setF_Classify_name(fC.getF_Classify_name());
		return goods;
	}
	
	/* 获取所有商品信息 */
	public ArrayList<GoodsInfo> AllGoodsList(){
		GoodsDao goodsDao=new GoodsDao();
		ArrayList<GoodsInfo> goodsL =goodsDao.getAllGoods();// goods获得商品
		
		GoodsImgInfo goodsImg=new GoodsImgInfo();//商品图片
		GoodsImgDao imgDao=new GoodsImgDao();
		
		ClassifyDao classify=new ClassifyDao();//类别
		FirstClassifyInfo fC=new FirstClassifyInfo();//1
		SecondClassifyInfo sC=new SecondClassifyInfo();//2
		ThridClassifyInfo tC=new ThridClassifyInfo();//3
		
		GoodsInfo gInfo=new GoodsInfo();//暂时存储一个goodsInfo
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();//存储
		
		for(int i=0;i<goodsL.size();i++){
			gInfo=new GoodsInfo();//暂时存储一个goodsInfo
			gInfo.setGoods_id(goodsL.get(i).getGoods_id());
			gInfo.setGoods_name(goodsL.get(i).getGoods_name());
			gInfo.setGoods_price(goodsL.get(i).getGoods_price());
			gInfo.setGoods_description(goodsL.get(i).getGoods_description());
			gInfo.setGoods_num(goodsL.get(i).getGoods_num());
			gInfo.setPurchase_times(goodsL.get(i).getPurchase_times());
			gInfo.setComment_times(goodsL.get(i).getComment_times());
			gInfo.setT_classify_id(goodsL.get(i).getT_classify_id());
			gInfo.setStatus(goodsL.get(i).getStatus());
			//图片
			goodsImg=imgDao.getOneGoodsImg(goodsL.get(i).getGoods_id());
			gInfo.setImg_id(goodsImg.getImg_id());
			gInfo.setImg_path(goodsImg.getImg_path());
			//类别
			tC=classify.getThridClassify(goodsL.get(i).getT_classify_id());//分类
			sC=classify.getSecondClassify(tC.getS_classify_id());//2
			fC=classify.getFirstClassify(sC.getF_classify_id());//1
			gInfo.setF_Classify_id(fC.getF_Classify_id());
			gInfo.setF_Classify_name(fC.getF_Classify_name());//1
			gInfo.setS_Classify_id(sC.getS_Classify_id());
			gInfo.setS_Classify_name(sC.getS_Classify_name());//2
			gInfo.setT_Classify_name(tC.getT_Classify_name());//3
			
			goodsList.add(gInfo);
		}
		return goodsList;
	}/* 获取所有商品信息 */
	
	/* 获取所有3级类别商品信息 */
	public ArrayList<GoodsInfo> AllThridGoodsList(String tName){
		ArrayList<GoodsInfo> all = AllGoodsList();//所有goodsList
		ArrayList<GoodsInfo> allThrid = new ArrayList<GoodsInfo>();//保存
		if(all.size()!=0){//如果所有商品数量不为空
		for(int i=0;i<all.size();i++){
			if(all.get(i).getT_Classify_name().equals(tName)){//如果属于第三级类别
				allThrid.add(all.get(i));
			}
		}
		}
		return allThrid;
	}
	
	
	//添加一个完整的Goods
	public int insertGoods(GoodsInfo goods){
		
		GoodsInfo goodsCplt = new GoodsInfo();//完整的goods
		GoodsInfo goodsC1 = new GoodsInfo();//在goods中插入的GoodsInfo
		GoodsDao goods1= new GoodsDao();
		goodsC1=goods1.insertOneGoods(goods);
		
		/*
		 * 当有第3级的时候，不再插入Classify
		 * 有第3级，看是否有第二级。看是否有1级
		 */
		ClassifyDao classify=new ClassifyDao();
		ArrayList<ThridClassifyInfo> tInfoList =classify.getAllThreeClassify();
		int tTem=0;//3
		ArrayList<SecondClassifyInfo> sInfoList =classify.getAllSecondClassify();
		int sTem=0;//2
		ArrayList<FirstClassifyInfo> fInfoList = classify.getAllFirstClassify();
		int fTem=0;//1
		//1
		for(int i=0;i<fInfoList.size();i++){
			if(goods.getF_Classify_name().equals(fInfoList.get(i).getF_Classify_name())){
				fTem=1;
				break;
			}//有第1级，不插入
		}
		if(fTem==0){//没有1，插入
			
		}
		//2
		for(int i=0;i<sInfoList.size();i++){
			if(goods.getS_Classify_name().equals(sInfoList.get(i).getS_Classify_name())){
				sTem=1;
				break;
			}//有第2级，不插入
		}
		if(sTem==0){//没有2，插入
			
		}
		//3
		for(int i=0;i<tInfoList.size();i++){
			if(goods.getT_Classify_name().equals(tInfoList.get(i).getT_Classify_name())){
				tTem=1;
				break;
			}//有第3级，不插入
		}
		if(tTem==0){//没有2，插入
			
		}
//		if(tTem==0){//没有第3级
//			//判断是否有第二级
//			for(int i=0;i<sInfoList.size();i++){
//				if(goods.getS_Classify_name().equals(sInfoList.get(i).getS_Classify_name())){
//					sTem=1;
//					break;
//				}//有第2级，不插入
//			}
//			if(sTem==0){//没有2
//				for(int i=0;i<fInfoList.size();i++){
//					if(goods.getF_Classify_name().equals(fInfoList.get(i).getF_Classify_name())){
//						fTem=1;
//						break;
//					}//有第1级，不插入
//				}
//				if(fTem==0){//没有1，插入
//					
//				}
//			}
//		}
		
        return 0;
   } 
}
