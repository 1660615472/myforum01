package com.cheer.bbs.service;

import com.cheer.bbs.pojo.User;
import com.cheer.bbs.pojo.Titles;

import java.util.List;

public interface TitService {
    List<Titles> getTitList();

    Titles getTitById(int id);

    int insertTitles(Titles tit);

    int update(Titles tit);

    int delete(int id);

    int selectNewTitle();

    List<Titles> getTitleListByCname(String cname);

    //查询每条帖子对应的回复数
    int selectcommentNumByid(int id);

    List<Titles> searchTitles(String searchContext);
}
