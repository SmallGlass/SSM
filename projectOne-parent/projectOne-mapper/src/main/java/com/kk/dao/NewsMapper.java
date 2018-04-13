package com.kk.dao;


import com.kk.pojo.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {
    List<News> findNewsByCriteria(@Param("criteria") String criteria);
    List<News> findNewsByTypeId(Integer typeId);
    List<News> findNewsAllByTypeId(@Param("typeId")int typeId,@Param("offset")int offset, @Param("pageSize") int pageSize);
    int countNewsAllByTypeId(int typeId);
}