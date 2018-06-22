package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.DBConnection;
import domain.AllClassifyInfo;
import domain.FirstClassifyInfo;
import domain.SecondClassifyInfo;
import domain.ThridClassifyInfo;

//商品类别
public class ClassifyDao {
	private Connection con = null;
	
	/* 获取一个商品所有分类信息   */
	public AllClassifyInfo getAllClassifyOfOne(int gtId) {//gtId:3级ID

		AllClassifyInfo classify=new AllClassifyInfo();//所有分类
		FirstClassifyInfo fClassify=null;//1
		SecondClassifyInfo sClassify=null;//2
		ThridClassifyInfo tClassify=null;//3
		
		tClassify=getThridClassify(gtId);//3
		classify.setT_Classify_id(gtId);
		classify.setT_Classify_name(tClassify.getT_Classify_name());
		
		sClassify = getSecondClassify(tClassify.getS_classify_id());//2
		classify.setS_Classify_id(sClassify.getS_Classify_id());
		classify.setS_Classify_name(sClassify.getS_Classify_name());
		
		fClassify = getFirstClassify(sClassify.getF_classify_id());//1
		classify.setF_Classify_id(fClassify.getF_Classify_id());
		classify.setF_Classify_name(fClassify.getF_Classify_name());
			
			
		return classify;
	}/* 获取所有分类信息 */
	
	/* 获取所有一级分类信息 */
	public ArrayList<FirstClassifyInfo> getAllFirstClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		FirstClassifyInfo fClassify = null;//商品1级分类
		ArrayList<FirstClassifyInfo> firstClassifyList = new ArrayList<FirstClassifyInfo>();
		
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT DISTINCT * FROM first_classify");
    		//pStmt.setString(1, fName);	//由1级名称查信息
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				fClassify = new FirstClassifyInfo();
				fClassify.setF_Classify_id(rs.getInt("f_Classify_id"));
				fClassify.setF_Classify_name(rs.getString("f_Classify_name"));//1级分类名称
				
				firstClassifyList.add(fClassify);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取所有1级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return firstClassifyList;
	}/* 获取1级分类信息 */
	
	/* 获取一条商品1级分类信息 */
	public FirstClassifyInfo getFirstClassify(int fId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		FirstClassifyInfo fClassify = null;//商品2级分类
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM first_classify where f_Classify_id=?");
			pStmt.setInt(1,fId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				fClassify = new FirstClassifyInfo();
				fClassify.setF_Classify_id(rs.getInt("f_Classify_id"));
				fClassify.setF_Classify_name(rs.getString("f_Classify_name"));//1级分类名称
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取一条1级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return fClassify;
	}/* 获取1级分类信息 */
	
	/* 获取所有一级id */
	public ArrayList<Integer> getFirstClassifyId(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		ArrayList<Integer> fIdList = new ArrayList<Integer>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT DISTINCT  f_Classify_id FROM first_classify");
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				fIdList.add(rs.getInt("f_Classify_id"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取1级id失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return fIdList;
	}/* 获取1级id信息 */
	
	
	/* 获取一条商品2级分类信息 */
	public SecondClassifyInfo getSecondClassify(int sId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//商品2级分类
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify where s_Classify_id=?");
    		
			pStmt.setInt(1,sId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2级分类名称
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取一条2级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return sClassify;
	}/* 获取2级分类信息 */
	
	/* 获取所有2级分类信息 */
	public ArrayList<SecondClassifyInfo> getAllSecondClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//商品2级分类
		ArrayList<SecondClassifyInfo> allClassify=new ArrayList<SecondClassifyInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify ");
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2级分类名称
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				allClassify.add(sClassify);
				
				//System.out.println("nameAll2: "+rs.getString("s_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取所有2级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return allClassify;
	}/* 获取所有2级分类信息 */
	
	/* 获取一系列商品2级分类信息 */
	public ArrayList<SecondClassifyInfo> getAllSecondClassifyById(int fId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//商品2级分类
		ArrayList<SecondClassifyInfo> allClassify=new ArrayList<SecondClassifyInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify where f_Classify_id=?");
			pStmt.setInt(1,fId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2级分类名称
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				allClassify.add(sClassify);
				
				System.out.println("name: "+rs.getString("s_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取2级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return allClassify;
	}/* 获取2级分类信息 */
	
	/* 获取所有商品3级分类信息 */
	public ArrayList<ThridClassifyInfo> getAllThreeClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		ThridClassifyInfo tClassify = null;//商品3级分类
		ArrayList<ThridClassifyInfo> tClassifyList = new ArrayList<ThridClassifyInfo>();//商品3级分类
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM thrid_classify");
    		rs = pStmt.executeQuery();
			while(rs.next()) {
				tClassify = new ThridClassifyInfo();
				tClassify.setT_Classify_id(rs.getInt("t_Classify_id"));
				tClassify.setT_Classify_name(rs.getString("t_Classify_name"));//三级分类名称
				tClassify.setS_classify_id(rs.getInt("s_Classify_id"));
				tClassifyList.add(tClassify);
				
				//System.out.println("nameAll33: "+rs.getString("t_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取所有三级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return tClassifyList;
	}/* 获取所有三级分类信息 */
	
	/* 获取一条商品3级分类信息 */
	public ThridClassifyInfo getThridClassify(int tId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		ThridClassifyInfo tClassify = null;//商品3级分类
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM thrid_classify where t_Classify_id=?");
    		pStmt.setInt(1,tId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				tClassify = new ThridClassifyInfo();
				tClassify.setT_Classify_id(rs.getInt("t_Classify_id"));
				tClassify.setT_Classify_name(rs.getString("t_Classify_name"));//三级分类名称
				tClassify.setS_classify_id(rs.getInt("s_Classify_id"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("获取一条商品三级分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return tClassify;
	}/* 获取三级分类信息 */
	
	/**************添加*有问题***************/
	//在first_classify添加分类信息
	public FirstClassifyInfo insertFirstClassify(String fName) {
		PreparedStatement pStmt = null;
//		FirstClassifyInfo fC = new FirstClassifyInfo();// 获得
//		fC = getFirstClassify(fId);
		FirstClassifyInfo f = new FirstClassifyInfo();// 添加
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("insert into first_classify (f_Classify_name) values(?)");

			pStmt.setString(1, fName);
//			pStmt.setString(2, fC.getF_Classify_name());
//			f.setF_Classify_id(fId);
//			f.setF_Classify_name(fC.getF_Classify_name());

			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("在first_classify添加分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return f;
	}   
		
	// 在second_classify添加分类信息
	public SecondClassifyInfo insertSecondClassify(int sId) {
		PreparedStatement pStmt = null;
		SecondClassifyInfo sC = new SecondClassifyInfo();// 获得
		sC = getSecondClassify(sId);
		SecondClassifyInfo s = new SecondClassifyInfo();// 添加
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("insert into second_classify values(?,?,?)");

			pStmt.setInt(1, sId);
			pStmt.setString(2, sC.getS_Classify_name());
			pStmt.setInt(3, sC.getF_classify_id());

			s.setS_Classify_id(sId);
			s.setS_Classify_name(sC.getS_Classify_name());
			s.setF_classify_id(sC.getF_classify_id());

			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("在second_classify添加分类信息失败！");
		} finally {
			DBConnection.closeConnection();
		}
		return s;
	}
	
	//在thrid_classify添加分类信息
	public ThridClassifyInfo insertThridClassify(int tId){//String tName
		PreparedStatement pStmt=null;
		ThridClassifyInfo tC=new ThridClassifyInfo();//获得
		tC=getThridClassify(tId);
		ThridClassifyInfo t=new ThridClassifyInfo();//添加
	    try {
	    	con=DBConnection.getConnection();
			pStmt = con.prepareStatement("insert into thrid_classify values(?,?,?)");
	        
			pStmt.setInt(1,tId);
			pStmt.setString(2,tC.getT_Classify_name());
			pStmt.setInt(3,tC.getS_classify_id());

			t.setT_Classify_id(tId);
			t.setT_Classify_name(tC.getT_Classify_name());
			t.setS_classify_id(tC.getS_classify_id());
			
			pStmt.close();
	    } catch (Exception e) {
	       	e.printStackTrace();
	        System.out.println("在thrid_classify添加分类信息失败！");
	    } finally{
	    	DBConnection.closeConnection();
		}		
	    return t;
	 }    
	
}
