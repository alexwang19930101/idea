package cn.itcast.mybatis.pojo.one2one;

public class Husband {

	private Integer hid;
	private String hname;

	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

	@Override
	public String toString() {
		return "Husband [hid=" + hid + ", hname=" + hname + "]";
	}

}
