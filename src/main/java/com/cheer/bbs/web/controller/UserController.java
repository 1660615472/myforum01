package com.cheer.bbs.web.controller;

import com.cheer.bbs.pojo.User;
import com.cheer.bbs.pojo.Titles;
import com.cheer.bbs.service.UserService;
import com.cheer.bbs.service.TitService;
import com.cheer.bbs.util.IOUtils;
import com.cheer.bbs.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Log4j2
@Controller
@RequestMapping({"/system", "/"})
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TitService titService;
    //登录
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    //登录
    @PostMapping("login")
    public String login(@RequestParam("name") String name, @RequestParam String password, HttpServletRequest request) {
        User user = userService.getUser ( name, password );
        if (user == null) {
            log.debug ( "账号或密码错误" );
        }

        if (user != null) {
            // TODO
            HttpSession session = request.getSession ();
            session.setAttribute ( "user", user );
            return "redirect:getPage"; //登录成功跳转到获取帖子信息的方法然后显示首页
        } else {
            return "redirect:/login";
        }
    }


    //首页显示用户信息的方法
    @GetMapping("/userInfo/{cname}")//thymeleaf页面的请求路径 获取传过来的发帖者名字
    public String avatar(Model model, @PathVariable String cname) {//@PathVariable String cname 取出url中的模板作为参数
        User user = this.userService.getUserByUserName ( cname );//用发帖者名字找到发帖者信息
        List<Titles> titles = this.titService.getTitleListByCname ( cname );//根据发帖者名字找到他所有的帖子
        model.addAttribute ( "titles", titles );//放入域
        model.addAttribute ( "user", user );
        return "userInfo";
    }

    @RequestMapping("/userRegister")
    public String userRegister() {
        return "userRegister";
    }

    @RequestMapping("/outLogin")
    public String outLogin(HttpSession session){
        //销毁会话，退出登录
        session.invalidate ();
        //退出登录后返回主页
        return "login";
    }


    @PostMapping("/userRegister")
    public String userRegister(User user, @RequestParam(required = false) MultipartFile file, HttpServletRequest request) throws Exception {
        String src = null;//声明路径
        String uploadFilename = null;
        log.debug ( file+">>>>>>>>>>>>>>" );
        if(file!=null){
             uploadFilename = file.getOriginalFilename ();//得到上传图片的文件名
        }
        if (user != null && uploadFilename != null) {
            src = "C:/avatar/";  //定义用户头像图片库路径
            String newFileName = user.getName () + uploadFilename;//图片名字
            log.debug ( newFileName + ">>>>>>" );
            File file2 = new File ( src, newFileName );//复制
            file.transferTo ( file2 );
            String fmt = "yyyy-MM-dd HH:mm";
            SimpleDateFormat sdf = new SimpleDateFormat ( fmt );
            String dateStr = sdf.format ( new Date () );
            user.setHiredate ( dateStr );//账号创建日期
            user.setAvatar ( newFileName );//设置用户头像名称
            //插入数据库注册成功
            int i = userService.insUser ( user );
            if (i == 1) {
                log.debug ( "注册成功！" );
            }
            String realPath = request.getServletContext().getRealPath("static/avatar");

            String dest = realPath + "/" + user.getAvatar();
            File avatar=new File(dest);
            if(!avatar.exists()){
                String newSrc = src+ user.getAvatar();
                IOUtils.copy(newSrc, dest);
            }

        }else if (user!=null){
            String fmt = "yyyy-MM-dd HH:mm";
            SimpleDateFormat sdf = new SimpleDateFormat ( fmt );
            String dateStr = sdf.format ( new Date () );
            user.setHiredate ( dateStr );//账号创建日期
            //插入数据库注册成功
            int i = userService.insUser ( user );
            if (i == 1) {
                log.debug ( "注册成功！" );
            }
        }

        return "redirect:/login";
    }
}
