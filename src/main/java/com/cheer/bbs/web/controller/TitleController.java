package com.cheer.bbs.web.controller;

import com.cheer.bbs.pojo.Messages;
import com.cheer.bbs.pojo.User;
import com.cheer.bbs.pojo.Titles;
import com.cheer.bbs.pojo.WebInfo;
import com.cheer.bbs.service.MesService;
import com.cheer.bbs.service.UserService;
import com.cheer.bbs.service.TitService;
import com.cheer.bbs.util.IOUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
@Controller
public class TitleController {
    @Resource
    private UserService userService;

    @Resource
    private MesService mesService;

    @Resource
    private TitService titService;

    @RequestMapping("getPage")
    public String login(Model model) {
        List<Titles> titList = titService.getTitList ();//找到所有帖子标题
        List<String> avalist = new ArrayList<> ();//定义一个存放头像的集合
        List<Integer> commentNumList = new ArrayList<> ();//回复数集合
        for (Titles titles : titList) { //遍历所有帖子
            User user = this.userService.getUserByUserName ( titles.getCname () );//通过帖子对应的用户名找到用户
            if (user.getAvatar () == null) {//判断user中有无头像：如果没有就是用默认的头像
                log.debug ( user.getAvatar () + ">>>>>>>>>>>>>>" );
                user.setAvatar ( "default.jpeg" );
            } else {//如果用户上传了头像就读取到项目指定文件夹
                ApplicationHome home = new ApplicationHome ( getClass () );//获取当前项目所在的目录
                File sysfile = home.getSource ();
                String jarPath = sysfile.getPath ();
                log.debug ( jarPath + ">>>>>>>>>>>>>>>>>" );
                String dest = jarPath + "/static/avatar/" + user.getAvatar ();
                log.debug ( dest + "项目头像存放路径" );
                File avatar = new File ( dest );//生成头像存放的文件夹
                if (!avatar.exists ()) {
                    String src = System.getProperty ( "user.home" ) + "/avatar/" + user.getAvatar ();//写入项目中指定文件夹
                    // IOUtils.copy ( src, dest );//把上传后的文件复制到指定项目文件夹中

                }
                avalist.add ( user.getAvatar () );//把用户头像存集合里
                //显示各帖子的回复数
                int commentNum = this.titService.selectcommentNumByid ( titles.getId () );
               titles.setComment ( commentNum );//给每条帖子设置查询到的回复数

            }
        }
        //显示网站运营状况一系列操作
        //查询会员注册人数
        List<User> userList = userService.getList ();
        //帖子数
        List<Titles> titList1 = titService.getTitList ();
        //交流，回帖数
        List<Messages> messagesList = mesService.getMessagesList ();

        WebInfo webInfo = new WebInfo ( userList.size (), titList1.size (), messagesList.size () );

        log.debug ( userList.size () + ">>>>>" + titList1.size () + ">>>>>>" + messagesList.size () );


        //把注册人数放入域
        model.addAttribute ( "userNum", webInfo.getUserNum () );
        model.addAttribute ( "messageNum", webInfo.getMessageNum () );
        model.addAttribute ( "TitleNum", webInfo.getTitleNum () );
        model.addAttribute ( "titlist", titList );
        model.addAttribute ( "avatarlist", avalist );
        return "index";

    }

    //搜索帖子（模糊查询）
    @PostMapping("searchTitles")
    public String searchTitles(@RequestParam(value = "searchContext") String searchContext,Model model){
        if(searchContext!=null) {
            List<Titles> searchTitles = titService.searchTitles (searchContext);
            model.addAttribute ( "searchTitles",searchTitles );
            return "searchTitles";
        }else {
            return "index";
        }
    }



    @RequestMapping("title")
    public String title() {
        return "title";
    }

    @PostMapping("title")
    public String title1(@RequestParam(value ="title" )String title
                         , @RequestParam(value = "message") String message, HttpSession session){

        //记录发帖时间，发帖人名字

        User user =(User) session.getAttribute ( "user" );
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//日期格式转换（数据库）
        Date time=new Date();

        String date = myFmt.format(time);
        log.debug ( date+"转换过的日期格式" );

        Titles titles = new Titles(user.getName(),title,message,date);//封装帖子信息
        //添加一条新的帖子
        int i = this.titService.insertTitles ( titles );//插入数据库
        if(i==1){
            log.debug ( "帖子插进数据库了！》》》》》》》" );
        }

        return "redirect:getPage";
    }


}
