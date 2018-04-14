package com.kk.controller;

import com.kk.dto.Page;
import com.kk.love.JsonDateValueProcessor;
import com.kk.love.ResponseUtil;
import com.kk.love.StringUtil;
import com.kk.pojo.News;
import com.kk.pojo.NewsType;
import com.kk.service.NewsService;
import com.kk.service.NewsTypeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
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
    @RequestMapping(value = "/newsList", method = {RequestMethod.GET})
    public String newsListUI(Model model,Integer typeId){
        List<NewsType> newsTypeList =newsTypeService.findNewsTypeAll();
        List<News> hotSpotNewsList =newsService.findNewsByCriteria(" where isHot=1 order by publishDate desc limit 0,8");
        List<News> newestNewsList =newsService.findNewsByCriteria(" order by publishDate desc limit 0,8");
        model.addAttribute("newsTypeList",newsTypeList);
        model.addAttribute("mainPage","../newsList.jsp");
        model.addAttribute("hotSpotNewsList",hotSpotNewsList);
        model.addAttribute("newestNewsList",newestNewsList);
        model.addAttribute("typeId",typeId);
        return "foreground/common/newsTemp";
    }
    @RequestMapping(value = "/newsList", method = {RequestMethod.POST})
    public String newsList(HttpServletResponse response,Integer typeId, @RequestParam(value = "pageIndex", required = false)Integer pageIndex)throws Exception{
        Page page=new Page();
        int count=newsService.countNewsAllByTypeId(typeId);
        List<News> newsList=null;
        if(pageIndex==null){
            newsList=newsService.findNewsAllByTypeId(typeId,0,page.getSize());
            page.setCurentNumber(1);
        }else{
            newsList=newsService.findNewsAllByTypeId(typeId,(pageIndex-1)*page.getSize(),page.getSize());
            page.setCurentNumber(pageIndex);
        }
        page.setLastNumber((count-1)/page.getSize()+1);
        JsonConfig jConfig = new JsonConfig();
        jConfig.registerJsonValueProcessor(Date.class,new JsonDateValueProcessor());
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(newsList,jConfig);
        result.put("list",jsonArray);
        result.put("page",page);
        ResponseUtil.write(response, result);
        return null;
    }
}
