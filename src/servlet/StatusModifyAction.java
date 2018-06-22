package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.ClassifyDao;
import dao.GoodsDao;
import dao.GoodsImgDao;
import dao.ManageGoodsDao;
import domain.GoodsInfo;

/**
 * �����ϼ��¼�//���
 */
//@WebServlet("/StatusModifyAction")
public class StatusModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public StatusModifyAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		GoodsDao goodsDao = new GoodsDao();
		//GoodsInfo goods=new GoodsInfo();
		String gIdAll[] = request.getParameterValues("delid");
		//���ǵ�����ĸ���ť��0�¼ܣ�1�ϼܣ�2���
		int UorD=Integer.parseInt(request.getParameter("whichButton"));
		//���¼�
		if (UorD == 1 || UorD == 0) {
			if (gIdAll != null) {
				 //ǰ�˵�ʹ����,���û�򹴵Ļ� , request.getParameterValues("delid")����յ�nullֵ
				int size = java.lang.reflect.Array.getLength(gIdAll);//���д�С
				for (int i = 0; i < size; i++) { 
					goodsDao.modifyGoodsStatus(Integer.parseInt(gIdAll[i]), UorD);//���¼�
					// System.out.println("*"+gIdAll[i]+"<br>");
				}
				// ����һ��for����������ȡ��
			} else {
			}
		// ���¼���goodsList
		ManageGoodsDao mgoodsDB=new ManageGoodsDao();
		ArrayList<GoodsInfo> AllGoodsList = new ArrayList<GoodsInfo>();
		AllGoodsList = mgoodsDB.AllGoodsList();
		request.getSession().setAttribute("AllGoodsList", AllGoodsList);// ��ʾ������ƷList
		//�ض���
		response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/manage_goods.jsp");
		}//���¼�e
		else if(UorD==2){//���
			// һ�����
			ClassifyDao allFirstClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allFirstClassify", allFirstClassifyDB.getAllFirstClassify());
			// �������
			ClassifyDao allSecondClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allSecondClassify", allSecondClassifyDB.getAllSecondClassify());
			// 3�����
			ClassifyDao allThreeClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allThreeClassify", allThreeClassifyDB.getAllThreeClassify());
			// �ض�����ҳ�档
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/goodsDetails.jsp");
		}//���
	}
}
