package cn.zifangsky.mapper;

import cn.zifangsky.model.UsrFunc;

public interface UsrFuncMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UsrFunc record);

    int insertSelective(UsrFunc record);

    UsrFunc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UsrFunc record);

    int updateByPrimaryKey(UsrFunc record);
}