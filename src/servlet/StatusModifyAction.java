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
 * 处理上架下架//添加
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
		//看是点击的哪个按钮：0下架，1上架，2添加
		int UorD=Integer.parseInt(request.getParameter("whichButton"));
		//上下架
		if (UorD == 1 || UorD == 0) {
			if (gIdAll != null) {
				 //前端的使用者,如果没打勾的话 , request.getParameterValues("delid")会接收到null值
				int size = java.lang.reflect.Array.getLength(gIdAll);//阵列大小
				for (int i = 0; i < size; i++) { 
					goodsDao.modifyGoodsStatus(Integer.parseInt(gIdAll[i]), UorD);//上下架
					// System.out.println("*"+gIdAll[i]+"<br>");
				}
				// 利用一个for将阵列资料取出
			} else {
			}
		// 重新加载goodsList
		ManageGoodsDao mgoodsDB=new ManageGoodsDao();
		ArrayList<GoodsInfo> AllGoodsList = new ArrayList<GoodsInfo>();
		AllGoodsList = mgoodsDB.AllGoodsList();
		request.getSession().setAttribute("AllGoodsList", AllGoodsList);// 显示所有商品List
		//重定向
		response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/manage_goods.jsp");
		}//上下架e
		else if(UorD==2){//添加
			// 一级类别
			ClassifyDao allFirstClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allFirstClassify", allFirstClassifyDB.getAllFirstClassify());
			// 二级类别
			ClassifyDao allSecondClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allSecondClassify", allSecondClassifyDB.getAllSecondClassify());
			// 3级类别
			ClassifyDao allThreeClassifyDB = new ClassifyDao();
			request.getSession().setAttribute("allThreeClassify", allThreeClassifyDB.getAllThreeClassify());
			// 重定向到主页面。
			response.sendRedirect(request.getContextPath()+"/shopping-back/jsp/goodsDetails.jsp");
		}//添加
	}
}
