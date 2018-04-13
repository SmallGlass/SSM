package com.kk.service.impl;

import com.kk.dao.NewsMapper;
import com.kk.pojo.News;
import com.kk.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yzb on 2018/4/12.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    public List<News> findNewsByCriteria(String criteria) {
        return newsMapper.findNewsByCriteria(criteria);
    }

    public List<News> findNewsByTypeId(Integer typeId) {
        return newsMapper.findNewsByTypeId(typeId);
    }

    public List<News> findNewsAllByTypeId(int typeId, int offset, int pageSize) {
        return newsMapper.findNewsAllByTypeId(typeId,offset,pageSize);
    }

    public int countNewsAllByTypeId(int typeId) {
        return newsMapper.countNewsAllByTypeId(typeId);
    }
}
