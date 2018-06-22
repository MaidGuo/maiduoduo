package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.ClassifyDao;
import dao.GoodsDao;
import dao.ManageGoodsDao;
import domain.GoodsInfo;
import domain.ThridClassifyInfo;

/**
 *显示3级类别的所有商品
 */
//@WebServlet("/GoodsThridListAction")
public class GoodsThridListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GoodsThridListAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得3级类别id
		int tid =Integer.parseInt(request.getParameter("thridId"));
		
		ArrayList<GoodsInfo> tGoodslist = new ArrayList<GoodsInfo>();//保存
		GoodsDao goodsDao=new GoodsDao();
		tGoodslist = goodsDao.getGoodsByTid(tid);//获取xx三级类别所有商品
		request.getSession().setAttribute("AllThridGoodsList",tGoodslist);
		//3
		ClassifyDao classifyDao=new ClassifyDao();
		ThridClassifyInfo tlist=classifyDao.getThridClassify(tid);
		
		request.getSession().setAttribute("ThridClassify",tlist.getT_Classify_name());
		//重定向
		response.sendRedirect(request.getContextPath()+"/shopping-web/jsp/goods1.jsp");
	}
}
