package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ManageGoodsDao;

/**
 * ��ʾһ����Ʒ
 */
//@WebServlet("/GoodsAppearAction")
public class GoodsAppearAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public GoodsAppearAction() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�ͻ����ύ����
		int id = Integer.parseInt(request.getParameter("goodsId"));
		ManageGoodsDao goodsInfo = new ManageGoodsDao();
		// ���һ����Ʒ������ʾ��Ϣ
		request.getSession().setAttribute("oneGoodsInfo", goodsInfo.goodsDetails(id));
		// �ض��򵽲�ѯ��ʾ�γ���Ϣ�б��servlet
		response.sendRedirect(request.getContextPath() + "/shopping-web/jsp/goods2.jsp");
	}

}
