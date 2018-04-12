package com.kk.dao;

import com.kk.pojo.Newstype;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface NewstypeMapper {
    @Delete({
        "delete from newstype",
        "where newsTypeId = #{newsTypeId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer newsTypeId);

    @Insert({
        "insert into newstype (newsTypeId, typeName)",
        "values (#{newsTypeId,jdbcType=INTEGER}, #{typeName,jdbcType=VARCHAR})"
    })
    int insert(Newstype record);

    int insertSelective(Newstype record);

    @Select({
        "select",
        "newsTypeId, typeName",
        "from newstype",
        "where newsTypeId = #{newsTypeId,jdbcType=INTEGER}"
    })
    @ResultMap("com.kk.dao.NewstypeMapper.BaseResultMap")
    Newstype selectByPrimaryKey(Integer newsTypeId);

    int updateByPrimaryKeySelective(Newstype record);

    @Update({
        "update newstype",
        "set typeName = #{typeName,jdbcType=VARCHAR}",
        "where newsTypeId = #{newsTypeId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Newstype record);
}