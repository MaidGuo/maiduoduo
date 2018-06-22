package dao;

import java.util.ArrayList;

import domain.FirstClassifyInfo;
import domain.GoodsImgInfo;
import domain.GoodsInfo;
import domain.ManageGoodsInfo;
import domain.SecondClassifyInfo;
import domain.ThridClassifyInfo;

public class ManageGoodsDao {
	//��ʾManage
	public ArrayList<ManageGoodsInfo> mAppear(){
		ArrayList<ManageGoodsInfo> manageG = new ArrayList<ManageGoodsInfo>();//�洢
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
			mgoods.setmId(goodsAll.get(i).getGoods_id());//��ƷID
			
			mgoods.setmName(goodsAll.get(i).getGoods_name());
			gImgInfo=goodsImgDao.getOneGoodsImg(goodsAll.get(i).getGoods_id());
			mgoods.setmPicPath(gImgInfo.getImg_path());//ͼƬ·��
	
			tC=classifyDB.getThridClassify(goodsAll.get(i).getT_classify_id());//����
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
	 * ��ʾgoodsDetails
	 * ��ʾһ������goods
	 */
	public GoodsInfo goodsDetails(int id){
		GoodsInfo goods=new GoodsInfo();//�洢
		GoodsInfo goodsOne=new GoodsInfo();
		GoodsDao goodsDao=new GoodsDao();
		goodsOne=goodsDao.getOneGoods(id);
		GoodsImgDao imgDB=new GoodsImgDao();
		GoodsImgInfo imgInfo = imgDB.getOneGoodsImg(id);
		//���
		ClassifyDao classifyDB = new ClassifyDao();
		FirstClassifyInfo fC=new FirstClassifyInfo();//1
		SecondClassifyInfo sC=new SecondClassifyInfo();//2
		ThridClassifyInfo tC=new ThridClassifyInfo();//3
		
		goods.setGoods_id(id);
		goods.setGoods_name(goodsOne.getGoods_name());//����
		goods.setGoods_price(goodsOne.getGoods_price());//�۸�
		goods.setGoods_num(goodsOne.getGoods_num());//�����
		goods.setPurchase_times(goodsOne.getPurchase_times());
		goods.setComment_times(goodsOne.getComment_times());
		goods.setStatus(goodsOne.getStatus());//״̬
		goods.setGoods_description(goodsOne.getGoods_description());
		goods.setImg_path(imgInfo.getImg_path());
		
		goods.setT_classify_id(goodsOne.getT_classify_id());//3
		tC=classifyDB.getThridClassify(goodsOne.getT_classify_id());//����
		sC=classifyDB.getSecondClassify(tC.getS_classify_id());//2
		fC=classifyDB.getFirstClassify(sC.getF_classify_id());//1
		goods.setT_Classify_name(tC.getT_Classify_name());
		goods.setS_Classify_name(sC.getS_Classify_name());
		goods.setF_Classify_name(fC.getF_Classify_name());
		return goods;
	}
	
	/* ��ȡ������Ʒ��Ϣ */
	public ArrayList<GoodsInfo> AllGoodsList(){
		GoodsDao goodsDao=new GoodsDao();
		ArrayList<GoodsInfo> goodsL =goodsDao.getAllGoods();// goods�����Ʒ
		
		GoodsImgInfo goodsImg=new GoodsImgInfo();//��ƷͼƬ
		GoodsImgDao imgDao=new GoodsImgDao();
		
		ClassifyDao classify=new ClassifyDao();//���
		FirstClassifyInfo fC=new FirstClassifyInfo();//1
		SecondClassifyInfo sC=new SecondClassifyInfo();//2
		ThridClassifyInfo tC=new ThridClassifyInfo();//3
		
		GoodsInfo gInfo=new GoodsInfo();//��ʱ�洢һ��goodsInfo
		ArrayList<GoodsInfo> goodsList = new ArrayList<GoodsInfo>();//�洢
		
		for(int i=0;i<goodsL.size();i++){
			gInfo=new GoodsInfo();//��ʱ�洢һ��goodsInfo
			gInfo.setGoods_id(goodsL.get(i).getGoods_id());
			gInfo.setGoods_name(goodsL.get(i).getGoods_name());
			gInfo.setGoods_price(goodsL.get(i).getGoods_price());
			gInfo.setGoods_description(goodsL.get(i).getGoods_description());
			gInfo.setGoods_num(goodsL.get(i).getGoods_num());
			gInfo.setPurchase_times(goodsL.get(i).getPurchase_times());
			gInfo.setComment_times(goodsL.get(i).getComment_times());
			gInfo.setT_classify_id(goodsL.get(i).getT_classify_id());
			gInfo.setStatus(goodsL.get(i).getStatus());
			//ͼƬ
			goodsImg=imgDao.getOneGoodsImg(goodsL.get(i).getGoods_id());
			gInfo.setImg_id(goodsImg.getImg_id());
			gInfo.setImg_path(goodsImg.getImg_path());
			//���
			tC=classify.getThridClassify(goodsL.get(i).getT_classify_id());//����
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
	}/* ��ȡ������Ʒ��Ϣ */
	
	/* ��ȡ����3�������Ʒ��Ϣ */
	public ArrayList<GoodsInfo> AllThridGoodsList(String tName){
		ArrayList<GoodsInfo> all = AllGoodsList();//����goodsList
		ArrayList<GoodsInfo> allThrid = new ArrayList<GoodsInfo>();//����
		if(all.size()!=0){//���������Ʒ������Ϊ��
		for(int i=0;i<all.size();i++){
			if(all.get(i).getT_Classify_name().equals(tName)){//������ڵ��������
				allThrid.add(all.get(i));
			}
		}
		}
		return allThrid;
	}
	
	
	//���һ��������Goods
	public int insertGoods(GoodsInfo goods){
		
		GoodsInfo goodsCplt = new GoodsInfo();//������goods
		GoodsInfo goodsC1 = new GoodsInfo();//��goods�в����GoodsInfo
		GoodsDao goods1= new GoodsDao();
		goodsC1=goods1.insertOneGoods(goods);
		
		/*
		 * ���е�3����ʱ�򣬲��ٲ���Classify
		 * �е�3�������Ƿ��еڶ��������Ƿ���1��
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
			}//�е�1����������
		}
		if(fTem==0){//û��1������
			
		}
		//2
		for(int i=0;i<sInfoList.size();i++){
			if(goods.getS_Classify_name().equals(sInfoList.get(i).getS_Classify_name())){
				sTem=1;
				break;
			}//�е�2����������
		}
		if(sTem==0){//û��2������
			
		}
		//3
		for(int i=0;i<tInfoList.size();i++){
			if(goods.getT_Classify_name().equals(tInfoList.get(i).getT_Classify_name())){
				tTem=1;
				break;
			}//�е�3����������
		}
		if(tTem==0){//û��2������
			
		}
//		if(tTem==0){//û�е�3��
//			//�ж��Ƿ��еڶ���
//			for(int i=0;i<sInfoList.size();i++){
//				if(goods.getS_Classify_name().equals(sInfoList.get(i).getS_Classify_name())){
//					sTem=1;
//					break;
//				}//�е�2����������
//			}
//			if(sTem==0){//û��2
//				for(int i=0;i<fInfoList.size();i++){
//					if(goods.getF_Classify_name().equals(fInfoList.get(i).getF_Classify_name())){
//						fTem=1;
//						break;
//					}//�е�1����������
//				}
//				if(fTem==0){//û��1������
//					
//				}
//			}
//		}
		
        return 0;
   } 
}
