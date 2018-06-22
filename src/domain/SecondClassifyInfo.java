package domain;
//二级分类
public class SecondClassifyInfo {
	private int s_Classify_id;
	private String s_Classify_name;//分类名
	private int f_Classify_id;//一级分类
	
	public int getS_Classify_id() {
		return s_Classify_id;
	}
	public void setS_Classify_id(int s_Classify_id) {
		this.s_Classify_id = s_Classify_id;
	}
	public String getS_Classify_name() {
		return s_Classify_name;
	}
	public void setS_Classify_name(String s_Classify_name) {
		this.s_Classify_name = s_Classify_name;
	}
	public int getF_classify_id() {
		return f_Classify_id;
	}
	public void setF_classify_id(int f_classify_id) {
		this.f_Classify_id = f_classify_id;
	}
	
}
