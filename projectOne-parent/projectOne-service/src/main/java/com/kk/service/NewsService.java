package com.kk.service;

import com.kk.pojo.News;

import java.util.List;

/**
 * Created by yzb on 2018/4/12.
 */
public interface NewsService {
    List<News> findNewsByCriteria(String criteria);
    List<News> findNewsByTypeId(Integer typeId);
}
