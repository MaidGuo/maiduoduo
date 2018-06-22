package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClassifyDao;
import dao.ManageGoodsDao;
import dao.NoticeDao;
import domain.NoticeInfo;

/**
 * ��ҳ
 */
//@WebServlet("/WebGoodsManageAction")
public class WebGoodsManageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public WebGoodsManageAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response); // ����doPost����
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//һ�����
		ClassifyDao allFirstClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allFirstClassify",allFirstClassifyDB.getAllFirstClassify());
		
		//�������
		ClassifyDao allSecondClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allSecondClassify",allSecondClassifyDB.getAllSecondClassify());
		
		//3�����
		ClassifyDao allThreeClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allThreeClassify",allThreeClassifyDB.getAllThreeClassify());
			
		//����goods
		ManageGoodsDao goodsDao=new ManageGoodsDao();
		request.getSession().setAttribute("allGoodsList",goodsDao.AllGoodsList());
		
		//
		NoticeDao noticeDao=new NoticeDao();
		request.getSession().setAttribute("allNoticeList",noticeDao.getAllNotice());
		
		// �ض�����ҳ�档
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/homePage.jsp");
	}
}
