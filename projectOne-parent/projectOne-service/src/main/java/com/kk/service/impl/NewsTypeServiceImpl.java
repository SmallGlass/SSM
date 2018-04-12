package com.kk.service.impl;

import com.kk.dao.NewsTypeMapper;
import com.kk.pojo.NewsType;
import com.kk.service.NewsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzb on 2018/4/12.
 */
@Service
public class NewsTypeServiceImpl implements NewsTypeService{
    @Autowired
    private NewsTypeMapper newsTypeMapper;
    public List<NewsType> findNewsTypeAll() {
        return newsTypeMapper.findNewsTypeAll();
    }
}
