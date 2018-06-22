package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.NoticeDao;
import domain.NoticeInfo;

/**
 * Servlet implementation class NoticeEditAction
 */
public class NoticeEditAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeEditAction() {
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
		NoticeDao noticeDB = new NoticeDao();
		NoticeInfo notice = new NoticeInfo();
		notice.setNotice_id(MyTools.strToint(request.getParameter("notice_id")));
		notice.setNotice_theme(MyTools.toChinese(request.getParameter("title")));
		notice.setCreated_time(request.getParameter("created_time"));
		notice.setUpdate_time(request.getParameter("update_time"));
		notice.setNotice_content(MyTools.toChinese(request.getParameter("content")));
		int count = noticeDB.getEditNotice(notice);
		if (count != 0) {
			response.sendRedirect(path+"/servlet/NoticeListAction");
		}
	}

}
