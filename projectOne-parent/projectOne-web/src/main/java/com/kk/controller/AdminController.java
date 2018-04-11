package com.kk.controller;



import com.kk.dto.Page;
import com.kk.love.ResponseUtil;
import com.kk.pojo.StudentInfo;
import com.kk.service.StudentInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by yzb on 2018/4/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "studentInfoImpl")
    private StudentInfoService studentInfoService;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);// 日志文件
    @RequestMapping(value="/showStudent")
    public String showStudent(Model model, @RequestParam(value = "pageIndex", required = false)Integer pageIndex){
        Page page=new Page();
        List<StudentInfo> studentList=null;
        int count=studentInfoService.countStudentsAll();
        if(pageIndex==null){
            studentList=studentInfoService.findStudentsAll(0,page.getSize());
            page.setCurentNumber(1);
        }else{
            studentList=studentInfoService.findStudentsAll((pageIndex-1)*(page.getSize()),page.getSize());
            page.setCurentNumber(pageIndex);
            if(pageIndex>1)page.setUpNumber(pageIndex-1);
            else page.setUpNumber(1);
        }
        page.setLastNumber((count-1)/page.getSize()+1);
        model.addAttribute("studentList",studentList);
        model.addAttribute("page",page);
        return "admin/showStudent";
    }
    @RequestMapping(value="/showStudentJson",method={RequestMethod.GET})
    @ResponseBody
    public String showStudentJson(HttpServletResponse response, @RequestParam(value = "pageIndex", required = false)Integer pageIndex)throws Exception{
        Page page=new Page();
        List<StudentInfo> studentList=null;
        int count=studentInfoService.countStudentsAll();
        if(pageIndex==null){
            studentList=studentInfoService.findStudentsAll(0,page.getSize());
            page.setCurentNumber(1);
        }else{
            studentList=studentInfoService.findStudentsAll((pageIndex-1)*(page.getSize()),page.getSize());
            page.setCurentNumber(pageIndex);
            if(pageIndex>1)page.setUpNumber(pageIndex-1);
            else page.setUpNumber(1);
        }
        page.setLastNumber((count-1)/page.getSize()+1);
        JSONObject result = new JSONObject();
        JSONArray jsonArray = JSONArray.fromObject(studentList);
        result.put("studentList",jsonArray);
        result.put("page",page);
        log.info("request:showStudentJson,studentList:"+studentList.toString());
        ResponseUtil.write(response, result);
        return null;
    }
    // 删除学生
    @RequestMapping(value = "/removeStudent", method = {RequestMethod.GET} )
    private String removeStudent(Integer id) throws Exception {
        if (id == null) {
            //如果id为空就返回学生显示页面
            return "admin/showStudent";
        }
        studentInfoService.deleteStudentById(id);
        return "redirect:showStudent";
    }
}
