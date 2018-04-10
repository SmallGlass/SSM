package com.kk.controller;

import com.kk.dto.Page;
import com.kk.pojo.StudentInfo;
import com.kk.service.StudentInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yzb on 2018/4/6.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    @Resource(name = "studentInfoImpl")
    private StudentInfoService studentInfoService;

    @RequestMapping("/showStudent")
    public String showStudent(Model model, Integer pageIndex){
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
