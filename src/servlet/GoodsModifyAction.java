package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageGoodsDao;
import domain.GoodsInfo;

/**
 * ��̨һ����Ʒ�ľ�����Ϣչʾ
 * Servlet implementation class GoodsModifyAction
 */
//@WebServlet("/GoodsModifyAction")
public class GoodsModifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GoodsModifyAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response); //����doPost����
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ�ͻ����ύ����
		int pd=Integer.parseInt(request.getParameter("goodsOrClass"));//�ж������2������Ʒ1
		int id=Integer.parseInt(request.getParameter("goodsId"));
		ManageGoodsDao goodsInfo = new ManageGoodsDao();
		// ���һ����Ʒ������ʾ��Ϣ
		request.getSession().setAttribute("oneGoodsInfo", goodsInfo.goodsDetails(id));
		if(pd==1){
		// �ض���
		response.sendRedirect(request.getContextPath() + "/shopping-back/jsp/goodsUpdate.jsp");
	
		}else{
			// �ض���
			response.sendRedirect(request.getContextPath() + "/shopping-back/jsp/goodsClassUpdate.jsp");
		
		}
	}

}
