package domain;

import java.util.List;

/**
 * 分页bean
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean<T> {
	//已知数据
	private int pageNum;//当前页，从请求那边传来
	private int pageSize;//每页显示的数据条数
	private int totalRecord;//总的记录条数
	
	//需要计算得来
	private int totalPage;//总页数，通过totalRecord和pageSize计算可以得来
	private int startIndex;//开始索引，数据库中从第几行拿，startIndex和pageSize确定每页需要显示的数据
	
	private List<T> list; //保存每页要显示的数据
	private int start; //分页需显示的页数，若页面：1,2,3,4,5  
	private int end; //则start:1   end:5
	
	public PageBean(int pageNum,int pageSize,int totalRecord) {
		this.pageNum=pageNum;
		this.pageSize=pageSize;
		this.totalRecord=totalRecord;
		//totalPage:总页数
		if(totalRecord%pageSize==0) {
			//整除，正好每页显示pageSize条数据，没有多余一页要显示少于pageSize条数据的
			this.totalPage=totalRecord/pageSize;
		}else {
			this.totalPage=totalRecord/pageSize+1;
		}
		//开始索引
		this.startIndex=(pageNum-1)*pageSize;  //???pageNum-1
		
		this.start=1;
		this.end=5;
		
		if(totalPage<=5) {
			this.end=this.totalPage;
		}else {
			//总页数大于5，根据当前是第几页，来判断start和end为多少
			this.start=pageNum-2;
			this.end=pageNum+2;
			
			if(start<=0) {
				this.start=1;
				this.end=5;
			}
			
			if(end>this.totalPage) {
				this.end=totalPage;
				this.start=end-5;
			}
		}
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
	
}

