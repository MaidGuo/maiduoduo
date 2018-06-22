package common;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyTools {
	public static int strToint(String str){			//��String������ת��Ϊint�����ݵķ���
		if(str==null||str.equals(""))
			str="0";
		int i=0;
		try{
			i=Integer.parseInt(str);
		}catch(NumberFormatException e){
			i=0;
			e.printStackTrace();
		}
		return i;		
	}
	public static float strToFloat(String str){			//��String������ת��Ϊfloat�����ݵķ���
		if(str==null||str.equals(""))
			str="0";
		float f=0.0f;
		try{
			f=Float.parseFloat(str);
		}catch(NumberFormatException e){
			f=0.0f;
			e.printStackTrace();
		}
		return f;		
	}
	public static String toChinese(String str){		//����ת������ķ���
		if(str==null)
			str="";
		try {
			str=new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			str="";
			e.printStackTrace();
		}
		return str;
	}
	public static int juggeSex(String str){
		int gender;
		if(str.equals("��")){
			gender=0;
		}else{
			gender=1;
		}
			
		return gender;
	}
	/**
	 * ��������ת��Ϊ�ַ�������
	 * @param date
	 * @return
	 */
	public static String transDateToString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = simpleDateFormat.format(date);
		return string; 
	}
	/**
	 * �ַ�����ʱ������ת��Ϊ��������
	 * @param str
	 * @return
	 */
	public static Date transStringToDate(String str){
		
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//simpleDateFormat = new SimpleDateFormat();
		 Date date = null;
		try {
			date = format1.parse(str);
			System.out.println("date: "+date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * ������λС��
	 * @param d
	 * @return
	 */
	public static double keepTwoDecimal(double d){
		//System.out.println("dd"+d);
		 DecimalFormat df = new DecimalFormat("#.00");
		  String newd=df.format(d);
		  d=Double.parseDouble(newd);
		//  System.out.println(d);
		  return d;
	}
	/**
	 * ����Ψһ������(ϵͳ��ǰʱ��+��λ�����)
	 * @return
	 */
	public static String buildOrderNo(){
		int r1=(int)(Math.random()*(10));//����2��0-9�������
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//һ��13λ��ʱ���
		String orderNo =String.valueOf(now)+String.valueOf(r1)+String.valueOf(r2);// ����ID	
		return orderNo;
	}
      /**
       * ���ü����㷨�����ַ�������
       * @param str   ��Ҫ���ܵ�����
      * @param algorithm ���õļ����㷨
     * @return �ֽ�����
      */
     public static byte[] EncryptionBytes(String str, String algorithm) {
         // ����֮�������ֽ�����
         byte[] bytes = null;
         String str2="";
         try {
             // ��ȡMD5�㷨ʵ�� �õ�һ��md5����ϢժҪ
             MessageDigest md = MessageDigest.getInstance(algorithm);
             //���Ҫ���м���ժҪ����Ϣ
             md.update(str.getBytes());
             //�õ���ժҪ
             bytes = md.digest();
           //  str2=new String(bytes);
         } catch (NoSuchAlgorithmException e) {
            System.out.println("�����㷨: "+ algorithm +" ������: ");
         }
         return null==bytes?null:bytes;
    }
     /**
           * ���ֽ�����ת�����ַ�������
           * @param bytes
           * @return
           */
          public static String BytesConvertToHexString(byte [] bytes) {
              StringBuffer sb = new StringBuffer();
              for (byte aByte : bytes) {
               String s=Integer.toHexString(0xff & aByte);
                  if(s.length()==1){
                     sb.append("0"+s);
                  }else{
                      sb.append(s);
                 }
              }
              return sb.toString();
          }
      
         /**
           * ���ü����㷨�����ַ�������
           * @param str   ��Ҫ���ܵ�����
           * @param algorithm ���õļ����㷨
          * @return �ֽ�����
           */
          public static String EncryptionStr(String str, String algorithm) {
              // ����֮�������ֽ�����
              byte[] bytes = EncryptionBytes(str,algorithm);
              return BytesConvertToHexString(bytes);
          }
//     public static void main(String[] args) {
//   	           String test1="test1";
//   	           String test2="QWERFVDSCX";
//   	          String test3="23423KJHkdfg";
//   	           String [] test={test1,test2,test3};
//   	          for (String s : test) {
//   	              String bytes=EncryptionStr(s,MD5);
//   	               System.out.println("���ݣ�" + s+" ����֮��Ľ��Ϊ��"+bytes.toString()+" �ֽ����鳤��Ϊ��"+bytes.length());
//   	          }
//   	       }
}