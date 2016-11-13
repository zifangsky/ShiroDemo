package cn.zifangsky.mapper;

import java.util.List;

import cn.zifangsky.model.UsrRole;
import cn.zifangsky.model.UsrUserRole;

public interface UsrUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsrUserRole record);

    int insertSelective(UsrUserRole record);

    UsrUserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsrUserRole record);

    int updateByPrimaryKey(UsrUserRole record);
    
    /**
     * 根据用户ID查询该用户的所有角色
     * @param userId  用户ID
     * @return  用户的所有角色
     */
    List<UsrRole> selectRolesByUserId(Integer userId);
}