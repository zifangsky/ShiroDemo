package cn.zifangsky.mapper;

import cn.zifangsky.model.UsrRole;

public interface UsrRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsrRole record);

    int insertSelective(UsrRole record);

    UsrRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsrRole record);

    int updateByPrimaryKey(UsrRole record);
}