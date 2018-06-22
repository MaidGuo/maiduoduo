package servlet;

import domain.MessageReplyInfo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.MyTools;
import dao.MessageDao;
import domain.MessageInfo;

/**
 * Servlet implementation class MessageReplyAction
 */
public class MessageReplyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageReplyAction() {
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
		String reply = MyTools.toChinese(request.getParameter("reply"));
		int message_id = MyTools.strToint(request.getParameter("check_s"));
		MessageDao messageDB = new MessageDao();
		ArrayList<MessageReplyInfo> messagelist = new ArrayList<MessageReplyInfo>();
		MessageInfo messageInfo = new MessageInfo();
		MessageReplyInfo mReply = new MessageReplyInfo();
		MessageReplyInfo messageReply = new MessageReplyInfo(); //瀹氫箟鏂扮殑鐣欒█
		messageInfo = messageDB.getMessage(message_id); //寰楀埌鎸囧畾鐣欒█鐨勫叏閮ㄤ俊鎭�
		messageInfo.setIsReply(1);
		messageDB.getEditReply(messageInfo); //淇敼鐣欒█鐘舵��
		messagelist = messageDB.getAllReply(); //寰楀埌鍏ㄩ儴鐨勭暀瑷�
		mReply = messagelist.get(messagelist.size()-1);
		int size = mReply.getId() + 1; //寰楀埌鏈�鍚庣殑id锛坕d浠�1寮�濮嬮�掑锛�
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//璁剧疆鏃ユ湡鏍煎紡
		String date = df.format(new Date());// 鑾峰彇褰撳墠绯荤粺鏃堕棿锛坣ew Date()涓鸿幏鍙栧綋鍓嶇郴缁熸椂闂达級
		messageReply.setId(size+1);
		messageReply.setMessage_id(message_id);
		messageReply.setContent(reply);
		messageReply.setTime(date);
		int count = messageDB.getInsertReply(messageReply); //娣诲姞鏂扮殑鐣欒█
		request.getSession().setAttribute("count", count);
		response.sendRedirect(path + "/servlet/MessageListAction");
	}

}
