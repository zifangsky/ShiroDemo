package cn.zifangsky.mapper;

import java.util.List;

import cn.zifangsky.model.UsrFunc;
import cn.zifangsky.model.UsrRoleFunc;

public interface UsrRoleFuncMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsrRoleFunc record);

    int insertSelective(UsrRoleFunc record);

    UsrRoleFunc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsrRoleFunc record);

    int updateByPrimaryKey(UsrRoleFunc record);
    
    /**
     * 根据角色ID查询该角色的所有权限（功能）
     * @param roleId  角色ID
     * @return  角色的所有权限
     */
    List<UsrFunc> selectFuncsByRoleId(Integer roleId);
}