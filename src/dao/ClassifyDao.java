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

//��Ʒ���
public class ClassifyDao {
	private Connection con = null;
	
	/* ��ȡһ����Ʒ���з�����Ϣ   */
	public AllClassifyInfo getAllClassifyOfOne(int gtId) {//gtId:3��ID

		AllClassifyInfo classify=new AllClassifyInfo();//���з���
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
	}/* ��ȡ���з�����Ϣ */
	
	/* ��ȡ����һ��������Ϣ */
	public ArrayList<FirstClassifyInfo> getAllFirstClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		FirstClassifyInfo fClassify = null;//��Ʒ1������
		ArrayList<FirstClassifyInfo> firstClassifyList = new ArrayList<FirstClassifyInfo>();
		
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT DISTINCT * FROM first_classify");
    		//pStmt.setString(1, fName);	//��1�����Ʋ���Ϣ
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				fClassify = new FirstClassifyInfo();
				fClassify.setF_Classify_id(rs.getInt("f_Classify_id"));
				fClassify.setF_Classify_name(rs.getString("f_Classify_name"));//1����������
				
				firstClassifyList.add(fClassify);
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ����1��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return firstClassifyList;
	}/* ��ȡ1��������Ϣ */
	
	/* ��ȡһ����Ʒ1��������Ϣ */
	public FirstClassifyInfo getFirstClassify(int fId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		FirstClassifyInfo fClassify = null;//��Ʒ2������
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM first_classify where f_Classify_id=?");
			pStmt.setInt(1,fId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				fClassify = new FirstClassifyInfo();
				fClassify.setF_Classify_id(rs.getInt("f_Classify_id"));
				fClassify.setF_Classify_name(rs.getString("f_Classify_name"));//1����������
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡһ��1��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return fClassify;
	}/* ��ȡ1��������Ϣ */
	
	/* ��ȡ����һ��id */
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
			System.out.println("��ȡ1��idʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return fIdList;
	}/* ��ȡ1��id��Ϣ */
	
	
	/* ��ȡһ����Ʒ2��������Ϣ */
	public SecondClassifyInfo getSecondClassify(int sId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//��Ʒ2������
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify where s_Classify_id=?");
    		
			pStmt.setInt(1,sId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2����������
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡһ��2��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return sClassify;
	}/* ��ȡ2��������Ϣ */
	
	/* ��ȡ����2��������Ϣ */
	public ArrayList<SecondClassifyInfo> getAllSecondClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//��Ʒ2������
		ArrayList<SecondClassifyInfo> allClassify=new ArrayList<SecondClassifyInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify ");
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2����������
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				allClassify.add(sClassify);
				
				//System.out.println("nameAll2: "+rs.getString("s_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ����2��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return allClassify;
	}/* ��ȡ����2��������Ϣ */
	
	/* ��ȡһϵ����Ʒ2��������Ϣ */
	public ArrayList<SecondClassifyInfo> getAllSecondClassifyById(int fId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		SecondClassifyInfo sClassify = null;//��Ʒ2������
		ArrayList<SecondClassifyInfo> allClassify=new ArrayList<SecondClassifyInfo>();
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM second_classify where f_Classify_id=?");
			pStmt.setInt(1,fId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				sClassify = new SecondClassifyInfo();
				sClassify.setS_Classify_id(rs.getInt("s_Classify_id"));
				sClassify.setS_Classify_name(rs.getString("s_Classify_name"));//2����������
				sClassify.setF_classify_id(rs.getInt("f_Classify_id"));
				allClassify.add(sClassify);
				
				System.out.println("name: "+rs.getString("s_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ2��������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return allClassify;
	}/* ��ȡ2��������Ϣ */
	
	/* ��ȡ������Ʒ3��������Ϣ */
	public ArrayList<ThridClassifyInfo> getAllThreeClassify(){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		ThridClassifyInfo tClassify = null;//��Ʒ3������
		ArrayList<ThridClassifyInfo> tClassifyList = new ArrayList<ThridClassifyInfo>();//��Ʒ3������
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM thrid_classify");
    		rs = pStmt.executeQuery();
			while(rs.next()) {
				tClassify = new ThridClassifyInfo();
				tClassify.setT_Classify_id(rs.getInt("t_Classify_id"));
				tClassify.setT_Classify_name(rs.getString("t_Classify_name"));//������������
				tClassify.setS_classify_id(rs.getInt("s_Classify_id"));
				tClassifyList.add(tClassify);
				
				//System.out.println("nameAll33: "+rs.getString("t_Classify_name"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡ��������������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return tClassifyList;
	}/* ��ȡ��������������Ϣ */
	
	/* ��ȡһ����Ʒ3��������Ϣ */
	public ThridClassifyInfo getThridClassify(int tId){
		ResultSet rs = null;
		PreparedStatement pStmt=null; 
		ThridClassifyInfo tClassify = null;//��Ʒ3������
		try {
			con = DBConnection.getConnection();
			pStmt = con.prepareStatement("SELECT * FROM thrid_classify where t_Classify_id=?");
    		pStmt.setInt(1,tId);
    		rs = pStmt.executeQuery();
    		
			while(rs.next()) {
				tClassify = new ThridClassifyInfo();
				tClassify.setT_Classify_id(rs.getInt("t_Classify_id"));
				tClassify.setT_Classify_name(rs.getString("t_Classify_name"));//������������
				tClassify.setS_classify_id(rs.getInt("s_Classify_id"));
			}
			rs.close();
			pStmt.close();
		} catch (Exception e) {
			System.out.println("��ȡһ����Ʒ����������Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return tClassify;
	}/* ��ȡ����������Ϣ */
	
	/**************���*������***************/
	//��first_classify��ӷ�����Ϣ
	public FirstClassifyInfo insertFirstClassify(String fName) {
		PreparedStatement pStmt = null;
//		FirstClassifyInfo fC = new FirstClassifyInfo();// ���
//		fC = getFirstClassify(fId);
		FirstClassifyInfo f = new FirstClassifyInfo();// ���
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
			System.out.println("��first_classify��ӷ�����Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return f;
	}   
		
	// ��second_classify��ӷ�����Ϣ
	public SecondClassifyInfo insertSecondClassify(int sId) {
		PreparedStatement pStmt = null;
		SecondClassifyInfo sC = new SecondClassifyInfo();// ���
		sC = getSecondClassify(sId);
		SecondClassifyInfo s = new SecondClassifyInfo();// ���
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
			System.out.println("��second_classify��ӷ�����Ϣʧ�ܣ�");
		} finally {
			DBConnection.closeConnection();
		}
		return s;
	}
	
	//��thrid_classify��ӷ�����Ϣ
	public ThridClassifyInfo insertThridClassify(int tId){//String tName
		PreparedStatement pStmt=null;
		ThridClassifyInfo tC=new ThridClassifyInfo();//���
		tC=getThridClassify(tId);
		ThridClassifyInfo t=new ThridClassifyInfo();//���
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
	        System.out.println("��thrid_classify��ӷ�����Ϣʧ�ܣ�");
	    } finally{
	    	DBConnection.closeConnection();
		}		
	    return t;
	 }    
	
}
