package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.CommentDao;

/**
 * Servlet implementation class SelectCommentListAction
 */
public class SelectCommentListAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCommentListAction() {
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
		String begin_date = request.getParameter("beijingdate");
		String end_date = request.getParameter("enddate");
		int count = MyTools.strToint(request.getParameter("select"));
		String style = "";
		CommentDao commentDB = new CommentDao();
		if (count == 0) {
			style = "好";
		}
		if (count == 1) {
			style = "一般";
		}
		if (count == 2) {
			style = "差";
		}
		request.getSession().setAttribute("commentlist", commentDB.getComment(begin_date, end_date, style));
		response.sendRedirect(path + "/shopping-back/jsp/comment.jsp");
	}

}
