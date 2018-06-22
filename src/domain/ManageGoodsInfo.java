package domain;

public class ManageGoodsInfo {
	private int mId;//商品ID
	private String mPicPath;//商品图片路径
	private String mName;//商品名
	
	private int mFCid;
	private int mSCid;
	private int mTCid;
	
	private String mFCname;//一级分类名称
	private String mSCname;//2级分类名称
	private String mTCname;//3级分类名称
	
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmPicPath() {
		return mPicPath;
	}
	public void setmPicPath(String mPicPath) {
		this.mPicPath = mPicPath;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	
	public String getmFCname() {
		return mFCname;
	}
	public void setmFCname(String mFCname) {
		this.mFCname = mFCname;
	}
	
	public String getmSCname() {
		return mSCname;
	}
	public void setmSCname(String mSCname) {
		this.mSCname = mSCname;
	}
	
	public String getmTCname() {
		return mTCname;
	}
	public void setmTCname(String mTCname) {
		this.mTCname = mTCname;
	}
	public int getmFCid() {
		return mFCid;
	}
	public void setmFCid(int mFCid) {
		this.mFCid = mFCid;
	}
	public int getmSCid() {
		return mSCid;
	}
	public void setmSCid(int mSCid) {
		this.mSCid = mSCid;
	}
	public int getmTCid() {
		return mTCid;
	}
	public void setmTCid(int mTCid) {
		this.mTCid = mTCid;
	}
}
