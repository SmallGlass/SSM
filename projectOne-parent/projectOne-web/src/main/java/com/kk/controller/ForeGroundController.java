package com.kk.controller;

import com.kk.love.StringUtil;
import com.kk.pojo.News;
import com.kk.pojo.NewsType;
import com.kk.service.NewsService;
import com.kk.service.NewsTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yzb on 2018/4/12.
 */
@Controller
@RequestMapping("/foreground")
public class ForeGroundController {
    @Resource(name = "newsTypeServiceImpl")
    private NewsTypeService newsTypeService;
    @Resource(name="newsServiceImpl")
    private NewsService newsService;
    @RequestMapping("/index")
    public String index(Model model){
        List<NewsType> newsTypeList =newsTypeService.findNewsTypeAll();
        List<News> imageNewsList = newsService.findNewsByCriteria(" where isImage=1 order by publishDate desc limit 0,5");
        List<News> headNewsList =newsService.findNewsByCriteria(" where isHead=1 order by publishDate desc limit 0,1");
        News headNews=headNewsList.get(0);
        headNews.setContent(StringUtil.Html2Text(headNews.getContent()));
        List<News> hotSpotNewsList =newsService.findNewsByCriteria(" where isHot=1 order by publishDate desc limit 0,8");
        List<News> newestNewsList =newsService.findNewsByCriteria(" order by publishDate desc limit 0,8");
        List allIndexNewsList=new ArrayList();
        if(newsTypeList!=null && newsTypeList.size()!=0){
            for(int i=0;i<newsTypeList.size();i++){
                NewsType newsType=newsTypeList.get(i);
                List<News> oneSubList=newsService.findNewsByTypeId(newsType.getNewsTypeId());
                allIndexNewsList.add(oneSubList);
            }
        }
        model.addAttribute("newsTypeList",newsTypeList);
        model.addAttribute("imageNewsList",imageNewsList);
        model.addAttribute("headNews",headNews);
        model.addAttribute("hotSpotNewsList",hotSpotNewsList);
        model.addAttribute("newestNewsList",newestNewsList);
        model.addAttribute("allIndexNewsList",allIndexNewsList);
        return "foreground/index";
    }
}
