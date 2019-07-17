package com.cheer.bbs.dao;

import com.cheer.bbs.pojo.User;
import com.cheer.bbs.pojo.Titles;

import java.util.List;

public interface TitleDao {
    //获取所有帖子集合
    List<Titles> getTitList();

    //通过帖子id获取对应帖子
    Titles getTitById(int id);

    //插入一条帖子
    int insertTitles(Titles tit);


    int update(Titles tit);

    int delete(int id);

    //查找最新的帖子
    int selectNewTitle();

    List<Titles> getTitleListByCname(String cname);

    //查询每条帖子对应的回复数
    int selectcommentNumByid(int id);

    //搜索帖子显示找到的帖子标题
    List<Titles> searchTitles(String searchContext);
}
