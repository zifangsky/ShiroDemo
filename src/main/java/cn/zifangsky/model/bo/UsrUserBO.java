package cn.zifangsky.model.bo;

import java.util.List;

import cn.zifangsky.model.UsrUser;

public class UsrUserBO extends UsrUser {
	private List<UsrRoleBO> usrRoleBOs;

	public List<UsrRoleBO> getUsrRoleBOs() {
		return usrRoleBOs;
	}

	public void setUsrRoleBOs(List<UsrRoleBO> usrRoleBOs) {
		this.usrRoleBOs = usrRoleBOs;
	}
}
