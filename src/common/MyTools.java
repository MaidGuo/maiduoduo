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
	public static int strToint(String str){			//将String型数据转换为int型数据的方法
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
	public static float strToFloat(String str){			//将String型数据转换为float型数据的方法
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
	public static String toChinese(String str){		//进行转码操作的方法
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
		if(str.equals("男")){
			gender=0;
		}else{
			gender=1;
		}
			
		return gender;
	}
	/**
	 * 日期类型转换为字符串类型
	 * @param date
	 * @return
	 */
	public static String transDateToString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String string = simpleDateFormat.format(date);
		return string; 
	}
	/**
	 * 字符串长时间类型转换为日期类型
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
	 * 保留两位小数
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
	 * 生成唯一订单号(系统当前时间+两位随机码)
	 * @return
	 */
	public static String buildOrderNo(){
		int r1=(int)(Math.random()*(10));//产生2个0-9的随机数
		int r2=(int)(Math.random()*(10));
		long now = System.currentTimeMillis();//一个13位的时间戳
		String orderNo =String.valueOf(now)+String.valueOf(r1)+String.valueOf(r2);// 订单ID	
		return orderNo;
	}
      /**
       * 采用加密算法加密字符串数据
       * @param str   需要加密的数据
      * @param algorithm 采用的加密算法
     * @return 字节数据
      */
     public static byte[] EncryptionBytes(String str, String algorithm) {
         // 加密之后所得字节数组
         byte[] bytes = null;
         String str2="";
         try {
             // 获取MD5算法实例 得到一个md5的消息摘要
             MessageDigest md = MessageDigest.getInstance(algorithm);
             //添加要进行计算摘要的信息
             md.update(str.getBytes());
             //得到该摘要
             bytes = md.digest();
           //  str2=new String(bytes);
         } catch (NoSuchAlgorithmException e) {
            System.out.println("加密算法: "+ algorithm +" 不存在: ");
         }
         return null==bytes?null:bytes;
    }
     /**
           * 把字节数组转化成字符串返回
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
           * 采用加密算法加密字符串数据
           * @param str   需要加密的数据
           * @param algorithm 采用的加密算法
          * @return 字节数据
           */
          public static String EncryptionStr(String str, String algorithm) {
              // 加密之后所得字节数组
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
//   	               System.out.println("数据：" + s+" 加密之后的结果为："+bytes.toString()+" 字节数组长度为："+bytes.length());
//   	          }
//   	       }
}