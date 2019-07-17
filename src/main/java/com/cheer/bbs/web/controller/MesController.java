package com.cheer.bbs.web.controller;

import com.cheer.bbs.pojo.Messages;
import com.cheer.bbs.pojo.User;
import com.cheer.bbs.pojo.Titles;
import com.cheer.bbs.service.MesService;
import com.cheer.bbs.service.UserService;
import com.cheer.bbs.service.TitService;
import com.cheer.bbs.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Log4j2
@Controller
public class MesController {
    @Resource
    private MesService mesService;

    @Resource
    private TitService titService;

    @Resource
    private UserService userService;

    @GetMapping("/titleContent/{id}")//内容和回帖消息
    public String titleContent(Model model, @PathVariable Integer id) throws Exception {
        //根据个人主页帖子id找到对应的帖子
        Titles tit = this.titService.getTitById (id);
        List<Messages> mesList = this.mesService.getMesListByTid(id);//根据帖子id找到哪些帖子是同一次问答
        List<String> avalist = new ArrayList<>();
        List<String> timelist = new ArrayList<>();

        for (Messages messages : mesList) {//遍历和楼主有关的的帖子
            User user = this.userService.getUserByUserName(messages.getCname());//根据帖子的用户名找到用户
            String avatar = user.getAvatar();
            avalist.add(avatar);
            //获取发帖时的时间
            String temp = messages.getDate();
            SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date  date= myFmt.parse(temp);
            //发帖时间
            long time1 = date.getTime()/1000;
            //获取当前回复的时间
            Date now=new Date();
            long time2 = now.getTime()/1000;
            long ss = time2 - time1;//计算
            String RepliesTime = StringUtils.long2String(ss);


            timelist.add(RepliesTime);
        }
        model.addAttribute("avalist",avalist);//头像集合
        model.addAttribute("title",tit);//个人帖子
        model.addAttribute("message",mesList);//
        model.addAttribute("timelist",timelist);
        return "oneTitleInfo";
    }


    @PostMapping("/Replies/{id}")//回帖
    public String insMes(@RequestParam(value ="message" ) String message, HttpSession session, @PathVariable Integer id){
        User user = (User)session.getAttribute ( "user" );
        //查询对应帖子的全部楼层，回帖后楼层+1
       /* int newfloor = this.mesService.selectfloorByTid(id)+1;*/
        // TODO
        int newfloor = this.mesService.selectAllFloorByTid(id)+1;

        log.debug ( newfloor+">>>>>>>>>>>>>" );
        //登记回复时间
        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date now=new Date();

        String date = myFmt.format(now);

        Messages messages = new Messages(id,message,newfloor,user.getName(),date);
        int i = this.mesService.insertMes(messages);
        return "redirect:/titleContent/"+id;
    }
}
