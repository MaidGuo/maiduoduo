package domain;
//三级分类
public class ThridClassifyInfo {
	private int t_Classify_id;//分类id
	private String t_Classify_name;//分类名称
	private int s_classify_id;//二级分类编号
	
	public int getT_Classify_id() {
		return t_Classify_id;
	}
	public void setT_Classify_id(int t_Classify_id) {
		this.t_Classify_id = t_Classify_id;
	}
	public String getT_Classify_name() {
		return t_Classify_name;
	}
	public void setT_Classify_name(String t_Classify_name) {
		this.t_Classify_name = t_Classify_name;
	}
	public int getS_classify_id() {
		return s_classify_id;
	}
	public void setS_classify_id(int s_classify_id) {
		this.s_classify_id = s_classify_id;
	}
	
}
