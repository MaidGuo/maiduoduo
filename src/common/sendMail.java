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
	// �����˵�ַ
	public static String senderAddress = "maiduoduoShop@163.com";
	
	// �������˻���
	public static String senderAccount = "maiduoduoShop@163.com";
	// �������˻�����
	public static String senderPassword = "maiduoduo123";
	public static final String smtpPort = "465"; //�˿�
	 //�����ʼ��ķ�������ַ
	     private String host = "smtp.163.com";
	 private UserInfo user;
	/**
	 * ���캯��
	 * @param user
	 */
	public  sendMail(UserInfo user) {
		this.user=user;
	}

	/**
	 * ��ô���һ���ʼ���ʵ������
	 * @param session
	 * @return
	 */
	public static MimeMessage getMimeMessage(Session session, UserInfo user) {
		String recipientAddress = user.getEmail(); //�ռ��˵�ַ
	
		// ����һ���ʼ���ʵ������
		MimeMessage msg = null;
		/**
		 * �����ռ��˵�ַ���������Ӷ���ռ��ˡ����͡����ͣ�����������һ�д�����д���� MimeMessage.RecipientType.TO:����
		 * MimeMessage.RecipientType.CC������ MimeMessage.RecipientType.BCC������
		 */
		try {
			msg = new MimeMessage(session);
			// ���÷����˵�ַ
			msg.setFrom(new InternetAddress(senderAddress));
			// �����ռ��˵�ַ
			msg.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
					recipientAddress));
			// �����ʼ�����
			msg.setSubject("���伤��", "UTF-8");
			// /�ʼ�������
			StringBuffer sb = new StringBuffer();
			sb.append("�װ���" + user.getName());
			sb.append("<br/>��л��ע�� ���๺���̳ǡ�Ϊ��֤�û�����ʹ�����ǵ�ϵͳ����������·��������԰�������ȷ�����ĵ����ʼ���ַ��Ч��<br/>");

			sb.append("<a href='http://localhost:8080/maiduoduo/ValidateEmailAction?id="
					+ user.getId()
					+ "&activeCode="
					+ user.getActiveCode()
					+ "'>");
			sb.append("http://localhost:8080/maiduoduo/ValidateEmailAction?id="
					+ user.getId() + "&activeCode=" + user.getActiveCode());

			sb.append("</a>");
			sb.append("<br/>����������䲻��ֱ�ӵ�������ӣ����������Ӹ��Ƶ��������ַ���з��ʡ�<br>���ʼ���ϵͳ�Զ����ͣ�����ظ���");
			// �����ʼ�����
			msg.setContent(sb.toString(), "text/html;charset=UTF-8");
			// �����ʼ��ķ���ʱ��,Ĭ����������
			msg.setSentDate(new Date());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return msg;
	}

     /** ��дrun������ʵ�֣���run�����з����ʼ���ָ�����û�
      * @see java.lang.Thread#run()
      */
     @Override
	  public void run() {
          try{
        	// 1�������ʼ��������Ĳ�������
      		Properties props = new Properties();
      		// �����û�����֤��ʽ
      		props.setProperty("mail.smtp.auth", "true");
      		// ���ô���Э��
      		props.setProperty("mail.transport.protocol", "smtp");
      		// ���÷����˵�SMTP��������ַ
      		props.setProperty("mail.smtp.host", host);
      		// ���÷������˿�
      		props.setProperty("mail.smtp.port", smtpPort);
      		// ʹ��JSSE��SSL socketfactory��ȡ��Ĭ�ϵ�socketfactory
      		props.setProperty("mail.smtp.socketFactory.class",
      				"javax.net.ssl.SSLSocketFactory");
      		// ֻ����SSL������, ���ڷ�SSL�����Ӳ�������
      		props.setProperty("mail.smtp.socketFactory.fallback", "false");
      		// ����smtp�˿�
      		props.setProperty("mail.smtp.socketFactory.port", smtpPort);
      		// 2��������������Ӧ�ó�������Ļ�����Ϣ�� Session ����
      		Session session = Session.getInstance(props);
      		// ���õ�����Ϣ�ڿ���̨��ӡ����
      		session.setDebug(true);
      		// 3�������ʼ���ʵ������
      		Message msg = getMimeMessage(session, user);
      		// 4������session�����ȡ�ʼ��������Transport
      		Transport transport = null;	      	
      			transport = session.getTransport();
      			// ���÷����˵��˻���������
      			transport.connect(senderAccount, senderPassword);
      			// �����ʼ��������͵������ռ��˵�ַ��message.getAllRecipients()
      			// ��ȡ�������ڴ����ʼ�����ʱ��ӵ������ռ���, ������, ������
      			transport.sendMessage(msg, msg.getAllRecipients());     	
      			// 5���ر��ʼ�����      			
      				transport.close();
      		// ���ֻ�뷢�͸�ָ�����ˣ���������д��
      		// transport.sendMessage(msg, new Address[]{new
      		// InternetAddress("xxx@qq.com")});

           }catch (Exception e) {
               throw new RuntimeException(e);
           }
       }

	  /**
	   * �жϼ������Ƿ���ȷ
	   * @param id �û�id
	   * @param validateCode ���ݵļ�����
	   * @return flag ��־��0 ����ʧ�� 1����ɹ� 2 �ѹ��� 3�Ѽ��� 4����δע�ᣩ
	   */
	public static int processActivate(int id, String validateCode) {
		// ���ݷ��ʲ㣬ͨ��email��ȡ�û���Ϣ
		int flag = 0;
		UserRegisterDao rDB = new UserRegisterDao();
		UserInfo user = rDB.getActiveCodebyId(id);

		// ��֤�û��Ƿ����
		if (user != null) {
			// ��֤�û�����״̬
			if (user.getStatus() == 0) {
				// /û����
				Date currentTime = new Date();// ��ȡ��ǰʱ��
		
				Date before =  user.getRegisterTime();
				long a = currentTime.getTime() - before.getTime();
				System.out.println("zhuceTime:"+before );
				System.out.println("now:"+currentTime);
				int cha = (int) a / (60 * 60 * 1000);
				System.out.println("cha��"+cha);
				// ��֤�����Ƿ����
				// currentTime.before(user.getRegisterTime());
				if (cha < 48) { // 48Сʱ����Ч
					// ��֤�������Ƿ���ȷ
					if (user.getActiveCode().equals(validateCode)) {
						// ����ɹ�,�������û��ļ���״̬��Ϊ�Ѽ���
				
						flag = rDB.updateStatebyId(user.getId()); // �ɹ�����1
					} else {
						flag = 0;// ("�����벻��ȷ");
					}
				} else {
					flag = 2;// ("�������ѹ��ڣ�");
				}
			} else {
				flag = 3;// ("�����Ѽ�����¼��");
			}
		} else {
			flag = 4;// ("������δע�ᣨ�����ַ�����ڣ���");
		}
		return flag;
	}

}
