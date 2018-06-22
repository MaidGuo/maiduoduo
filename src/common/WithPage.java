package common;
import dao.*;
import domain.*;

import java.util.*;

/**
 * 分页逻辑层
 * @author Administrator
 *
 */
public class WithPage {
//	AdminDao admin;
	UserDao user;
	public WithPage() {
	//	admin=new AdminDao();
		user=new UserDao();
	}
	
	/**
	 * 找到pageNum页的所有用户数据
	 * @param pageNum  当前页
	 * @param pageSize  每页显示的数量
	 * @return
	 */
	public PageBean<UserInfo> findAllUser(int pageNum,int pageSize,List<UserInfo> userlist){
		int totalRecord=userlist.size();
		
		//创建pageBean对象
		PageBean pb=new PageBean(pageNum,pageSize,totalRecord);
		
		//获取PageBean对象中的startIndex
		int startIndex=pb.getStartIndex();
		int length=startIndex+pageSize;
		if(length>pb.getTotalRecord()) {
			length=pb.getTotalRecord();
		}
		List<UserInfo> user=new ArrayList<UserInfo>();
		if(length>0) {
			for(int i=startIndex;i<length;i++) {
				user.add(userlist.get(i));
			}
		}
		System.out.println("输出当前页数：pageNum="+pageNum);
		System.out.println("findAllUser输出:startIndex="+startIndex+"pageSize="+pageSize+"totalRecord="+totalRecord);
		//有startIndex和pageSize,就可以拿到每页的数据了
		pb.setList(user);  //将每页要显示的数据保存在PageBean中
		return pb;
	}
	/**
	 *  找到pageNum页的所有管理员数据
	 * @param pageNum 当前页
	 * @param pageSize 每页显示的数量
	 * @return
	 */
	public PageBean<AdminInfo> findAllAdmin(int pageNum,int pageSize,List<AdminInfo> adminlist){
		int totalRecord=adminlist.size();
		
		//创建pageBean对象
		PageBean<AdminInfo> pb=new PageBean<AdminInfo>(pageNum,pageSize,totalRecord);
		
		//获取PageBean对象中的startIndex
		int startIndex=pb.getStartIndex();
		int length=startIndex+pageSize;
		if(length>pb.getTotalRecord()) {
			length=pb.getTotalRecord();
		}
		List<AdminInfo> admin=new ArrayList<AdminInfo>();
		if(length>0) {
			for(int i=startIndex;i<length;i++) {
				System.out.println("findAllAdmin输出：totalRecord="+totalRecord+",i="+i);
				admin.add(adminlist.get(i));
			}
		}
		System.out.println("findAllAdmin输出:startIndex="+startIndex+"pageSize="+pageSize+"totalRecord="+totalRecord);	
		//有startIndex和pageSize,就可以拿到每页的数据了
		pb.setList(admin);  //将每页要显示的数据保存在PageBean中
		return pb;
	}
	
	/**
	 * 保存用户评论当前页的所有内容
	 * @param pageNum
	 * @param pageSize
	 * @param list
	 * @return
	 */
	public PageBean<CommentInfo> findAllReview(int pageNum,int pageSize,List<CommentInfo> reviewlist ){
		int totalRecord=reviewlist.size();
		//创建pageBean对象
		PageBean<CommentInfo> pb=new PageBean<CommentInfo>(pageNum,pageSize,totalRecord);
		//获取PageBean对象中的startIndex
		int startIndex=pb.getStartIndex();
		int length=startIndex+pageSize;
		if(length>pb.getTotalRecord()) {
			length=pb.getTotalRecord();
		}
		List<CommentInfo> review=new ArrayList<CommentInfo>();
		if(length>0) {
			for(int i=startIndex;i<length;i++) {
				review.add(reviewlist.get(i));
			}
		}
		pb.setList(review);	
		return pb;
	}
	
	/**
	 * 保存用户留言当前页的所有内容
	 * @param pageNum
	 * @param pageSize
	 * @param messagelist
	 * @return
	 */
	public PageBean<MessageInfo> findAllMessage(int pageNum,int pageSize,List<MessageInfo> messagelist){
		int totalRecord=messagelist.size();
		//创建pageBean对象
		PageBean<MessageInfo> pb=new PageBean<MessageInfo>(pageNum,pageSize,totalRecord);
		//获取PageBean对象中的startIndex
		int startIndex=pb.getStartIndex();
		int length=startIndex+pageSize;
		if(length>pb.getTotalRecord()) {
			length=pb.getTotalRecord();
		}
		List<MessageInfo> message=new ArrayList<MessageInfo>();
		if(length>0) {
			for(int i=startIndex;i<length;i++) {
				message.add(messagelist.get(i));
			}
		}
		pb.setList(message);	
		return pb;
	}
}
