package cn.itcast.mybatis.pojo;

import java.util.ArrayList;
import java.util.List;

public class QueryVo {

	private User user;
	private List<Integer> ids = new ArrayList<Integer>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
