package cn.zifangsky.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zifangsky.common.PageInfo;
import cn.zifangsky.manager.UsrUserManager;
import cn.zifangsky.mapper.UsrRoleFuncMapper;
import cn.zifangsky.mapper.UsrUserMapper;
import cn.zifangsky.mapper.UsrUserRoleMapper;
import cn.zifangsky.model.UsrFunc;
import cn.zifangsky.model.UsrRole;
import cn.zifangsky.model.UsrUser;
import cn.zifangsky.model.bo.UsrRoleBO;
import cn.zifangsky.model.bo.UsrUserBO;
import cn.zifangsky.utils.EncryptionUtil;

@Service("usrUserManager")
public class UsrUserManagerImpl implements UsrUserManager {
	private static Logger logger = Logger.getLogger("ROBOT");
	
	@Autowired
	private UsrUserMapper userMapper;
	
	@Autowired
	private UsrUserRoleMapper userRoleMapper;

	@Autowired
	private UsrRoleFuncMapper roleFuncMapper;

	@Override
	@Transactional(rollbackFor={Exception.class})
	public int deleteByPrimaryKey(Integer id) {
		return userMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public int insert(UsrUser usrUser) {
		return userMapper.insert(usrUser);
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public int insertSelective(UsrUser usrUser) {
		return userMapper.insertSelective(usrUser);
	}

	@Override
	public UsrUser selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public int updateByPrimaryKeySelective(UsrUser usrUser) {
		return userMapper.updateByPrimaryKeySelective(usrUser);
	}

	@Override
	@Transactional(rollbackFor={Exception.class})
	public int updateByPrimaryKey(UsrUser usrUser) {
		return userMapper.updateByPrimaryKey(usrUser);
	}

	@Override
	public int findAllCount(UsrUser usrUser) {
		return userMapper.findAllCount(usrUser);
	}

	@Override
	public List<UsrUser> findAll(PageInfo pageInfo, UsrUser usrUser) {
		return userMapper.findAll(pageInfo, usrUser);
	}

	@Override
	public UsrUserBO login(String username, String password) {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			UsrUserBO result = new UsrUserBO();
			// 密码采用sha256加密
			UsrUser user = userMapper.selectByNamePasswd(username, EncryptionUtil.sha256Hex(password));
			logger.info("登录，用户： " + username);
			
			if(user != null){
				//根据用户id查询所有角色
				List<UsrRole> roles = userRoleMapper.selectRolesByUserId(user.getId());
				
				List<UsrRoleBO> roleBOs = new ArrayList<>();
				if(roles != null && roles.size() > 0){
					for(UsrRole role : roles){
						//根据角色id查询所有权限
						List<UsrFunc> funcs = roleFuncMapper.selectFuncsByRoleId(role.getId());
						
						UsrRoleBO roleBO = new UsrRoleBO();
						roleBO.setId(role.getId());
						roleBO.setRolename(role.getRolename());
						roleBO.setDescription(role.getDescription());
						roleBO.setFuncs(funcs);  //角色对应的所有权限
						
						roleBOs.add(roleBO);
					}
				}
				result.setId(user.getId());
				result.setUsername(user.getUsername());
				result.setPassword(user.getPassword());
				result.setMobile(user.getMobile());
				result.setEmail(user.getEmail());
				result.setChannelid(user.getChannelid());
				result.setCreatetime(user.getCreatetime());
				result.setUpdatetime(user.getUpdatetime());
				result.setStatus(user.getStatus());
				result.setUsrRoleBOs(roleBOs);  //用户对应的所有角色
				
				return result;
			}
			return null;
		}
		return null;
	}

	@Override
	public void logout(HttpSession session) {
		session.removeAttribute("user");
	}

}
