package cn.itcast.mybatis.pojo.one2one;

public class Wife {

	private Integer wid;
	private String wname;

	private Husband husband;

	public Integer getWid() {
		return wid;
	}

	public void setWid(Integer wid) {
		this.wid = wid;
	}

	public String getWname() {
		return wname;
	}

	public void setWname(String wname) {
		this.wname = wname;
	}

	public Husband getHusband() {
		return husband;
	}

	public void setHusband(Husband husband) {
		this.husband = husband;
	}

	@Override
	public String toString() {
		return "Wife [wid=" + wid + ", wname=" + wname + ", husband=" + husband + "]";
	}

}