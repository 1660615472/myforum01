package com.cheer.bbs.dao;

import com.cheer.bbs.pojo.Messages;

import java.util.List;

public interface MesDao {
    //通过帖子id查找对应帖子集合
    List<Messages> getMesListByTid(int tid);

    int insertMes(Messages messages);

    int selectfloorByTid(int tid);

    int selectAllFloorByTid(int tid);

    List<Messages> getMessagesList();
}
