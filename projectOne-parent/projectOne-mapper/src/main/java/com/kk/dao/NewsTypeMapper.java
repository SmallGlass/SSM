package com.kk.dao;


import com.kk.pojo.NewsType;

import java.util.List;

public interface NewsTypeMapper {
    /*找到所有的新闻类型*/
    List<NewsType> findNewsTypeAll();
}