package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.NoticeDao;
import domain.NoticeInfo;

/**
 * Servlet implementation class NoticeAddAction
 */

public class NoticeAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeAddAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getContextPath(); 
		NoticeInfo notice = new NoticeInfo();
		NoticeInfo noticeInfo = new NoticeInfo();
		ArrayList<NoticeInfo> NoticeList = new ArrayList<NoticeInfo>(); //鑾峰彇琛ㄤ腑鏈�澶d锛坕d浠�1閫掑锛�
		NoticeDao noticeDB = new NoticeDao();
		NoticeList = noticeDB.getAllNotice();
		noticeInfo = NoticeList.get(NoticeList.size()-1); //寰楀埌鏈�鍚庝竴涓暀瑷�鐨勪俊鎭�
		notice.setNotice_id(noticeInfo.getNotice_id()+1); //灏嗘渶鍚庣暀瑷�鐨刬d+1鍗充负鏂扮暀瑷�id
		notice.setNotice_theme(MyTools.toChinese(request.getParameter("title")));
		notice.setCreated_time(request.getParameter("created_time"));
		notice.setUpdate_time(request.getParameter("update_time"));
		notice.setNotice_content(MyTools.toChinese(request.getParameter("content")));
		int count = noticeDB.getInsertNotice(notice);
		if (count != 0) {
			response.sendRedirect(path+"/servlet/NoticeListAction");
		}
	}

}
