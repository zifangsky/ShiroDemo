package cn.zifangsky.model.bo;

import java.util.List;

import cn.zifangsky.model.UsrFunc;
import cn.zifangsky.model.UsrRole;

public class UsrRoleBO extends UsrRole {
	private List<UsrFunc> funcs;

	public List<UsrFunc> getFuncs() {
		return funcs;
	}

	public void setFuncs(List<UsrFunc> funcs) {
		this.funcs = funcs;
	}
}
