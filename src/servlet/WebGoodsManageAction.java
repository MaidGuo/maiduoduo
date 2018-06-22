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
 * 首页
 */
//@WebServlet("/WebGoodsManageAction")
public class WebGoodsManageAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public WebGoodsManageAction() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response); // 调用doPost方法
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//一级类别
		ClassifyDao allFirstClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allFirstClassify",allFirstClassifyDB.getAllFirstClassify());
		
		//二级类别
		ClassifyDao allSecondClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allSecondClassify",allSecondClassifyDB.getAllSecondClassify());
		
		//3级类别
		ClassifyDao allThreeClassifyDB=new ClassifyDao();
		request.getSession().setAttribute("allThreeClassify",allThreeClassifyDB.getAllThreeClassify());
			
		//所有goods
		ManageGoodsDao goodsDao=new ManageGoodsDao();
		request.getSession().setAttribute("allGoodsList",goodsDao.AllGoodsList());
		
		//
		NoticeDao noticeDao=new NoticeDao();
		request.getSession().setAttribute("allNoticeList",noticeDao.getAllNotice());
		
		// 重定向到主页面。
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/homePage.jsp");
	}
}
