package common;

import java.util.*;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.UserRegisterDao;
import domain.UserInfo;

public class sendMail extends Thread {
	// 发件人地址
	public static String senderAddress = "maiduoduoShop@163.com";
	
	// 发件人账户名
	public static String senderAccount = "maiduoduoShop@163.com";
	// 发件人账户密码
	public static String senderPassword = "maiduoduo123";
	public static final String smtpPort = "465"; //端口
	 //发送邮件的服务器地址
	     private String host = "smtp.163.com";
	 private UserInfo user;
	/**
	 * 构造函数
	 * @param user
	 */
	public  sendMail(UserInfo user) {
		this.user=user;
	}

	/**
	 * 获得创建一封邮件的实例对象
	 * @param session
	 * @return
	 */
	public static MimeMessage getMimeMessage(Session session, UserInfo user) {
		String recipientAddress = user.getEmail(); //收件人地址
	
		// 创建一封邮件的实例对象
		MimeMessage msg = null;
		/**
		 * 设置收件人地址（可以增加多个收件人、抄送、密送），即下面这一行代码书写多行 MimeMessage.RecipientType.TO:发送
		 * MimeMessage.RecipientType.CC：抄送 MimeMessage.RecipientType.BCC：密送
		 */
		try {
			msg = new MimeMessage(session);
			// 设置发件人地址
			msg.setFrom(new InternetAddress(senderAddress));
			// 设置收件人地址
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					recipientAddress));
			// 设置邮件主题
			msg.setSubject("邮箱激活", "UTF-8");
			// /邮件的内容
			StringBuffer sb = new StringBuffer();
			sb.append("亲爱的" + user.getName());
			sb.append("<br/>感谢您注册 买多多购物商城。为保证用户正常使用我们的系统，请您点击下方的链接以帮助我们确认您的电子邮件地址有效：<br/>");

			sb.append("<a href='http://localhost:8080/maiduoduo/ValidateEmailAction?id="
					+ user.getId()
					+ "&activeCode="
					+ user.getActiveCode()
					+ "'>");
			sb.append("http://localhost:8080/maiduoduo/ValidateEmailAction?id="
					+ user.getId() + "&activeCode=" + user.getActiveCode());

			sb.append("</a>");
			sb.append("<br/>如果您的邮箱不能直接点击此链接，请您将链接复制到浏览器地址栏中访问。<br>此邮件由系统自动发送，请勿回复。");
			// 设置邮件正文
			msg.setContent(sb.toString(), "text/html;charset=UTF-8");
			// 设置邮件的发送时间,默认立即发送
			msg.setSentDate(new Date());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}

     /** 重写run方法的实现，在run方法中发送邮件给指定的用户
      * @see java.lang.Thread#run()
      */
     @Override
	  public void run() {
          try{
        	// 1、连接邮件服务器的参数配置
      		Properties props = new Properties();
      		// 设置用户的认证方式
      		props.setProperty("mail.smtp.auth", "true");
      		// 设置传输协议
      		props.setProperty("mail.transport.protocol", "smtp");
      		// 设置发件人的SMTP服务器地址
      		props.setProperty("mail.smtp.host", host);
      		// 设置服务器端口
      		props.setProperty("mail.smtp.port", smtpPort);
      		// 使用JSSE的SSL socketfactory来取代默认的socketfactory
      		props.setProperty("mail.smtp.socketFactory.class",
      				"javax.net.ssl.SSLSocketFactory");
      		// 只处理SSL的连接, 对于非SSL的连接不做处理
      		props.setProperty("mail.smtp.socketFactory.fallback", "false");
      		// 设置smtp端口
      		props.setProperty("mail.smtp.socketFactory.port", smtpPort);
      		// 2、创建定义整个应用程序所需的环境信息的 Session 对象
      		Session session = Session.getInstance(props);
      		// 设置调试信息在控制台打印出来
      		session.setDebug(true);
      		// 3、创建邮件的实例对象
      		Message msg = getMimeMessage(session, user);
      		// 4、根据session对象获取邮件传输对象Transport
      		Transport transport = null;	      	
      			transport = session.getTransport();
      			// 设置发件人的账户名和密码
      			transport.connect(senderAccount, senderPassword);
      			// 发送邮件，并发送到所有收件人地址，message.getAllRecipients()
      			// 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
      			transport.sendMessage(msg, msg.getAllRecipients());     	
      			// 5、关闭邮件连接      			
      				transport.close();
      		// 如果只想发送给指定的人，可以如下写法
      		// transport.sendMessage(msg, new Address[]{new
      		// InternetAddress("xxx@qq.com")});

           }catch (Exception e) {
               throw new RuntimeException(e);
           }
       }

	  /**
	   * 判断激活码是否正确
	   * @param id 用户id
	   * @param validateCode 传递的激活码
	   * @return flag 标志（0 激活失败 1激活成功 2 已过期 3已激活 4邮箱未注册）
	   */
	public static int processActivate(int id, String validateCode) {
		// 数据访问层，通过email获取用户信息
		int flag = 0;
		UserRegisterDao rDB = new UserRegisterDao();
		UserInfo user = rDB.getActiveCodebyId(id);

		// 验证用户是否存在
		if (user != null) {
			// 验证用户激活状态
			if (user.getStatus() == 0) {
				// /没激活
				Date currentTime = new Date();// 获取当前时间
		
				Date before =  user.getRegisterTime();
				long a = currentTime.getTime() - before.getTime();
				System.out.println("zhuceTime:"+before );
				System.out.println("now:"+currentTime);
				int cha = (int) a / (60 * 60 * 1000);
				System.out.println("cha："+cha);
				// 验证链接是否过期
				// currentTime.before(user.getRegisterTime());
				if (cha < 48) { // 48小时内有效
					// 验证激活码是否正确
					if (user.getActiveCode().equals(validateCode)) {
						// 激活成功,并更新用户的激活状态，为已激活
				
						flag = rDB.updateStatebyId(user.getId()); // 成功等于1
					} else {
						flag = 0;// ("激活码不正确");
					}
				} else {
					flag = 2;// ("激活码已过期！");
				}
			} else {
				flag = 3;// ("邮箱已激活，请登录！");
			}
		} else {
			flag = 4;// ("该邮箱未注册（邮箱地址不存在）！");
		}
		return flag;
	}

}
