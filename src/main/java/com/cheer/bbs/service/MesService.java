package com.cheer.bbs.service;

import com.cheer.bbs.pojo.Messages;

import java.util.List;

public interface MesService {
    List<Messages> getMesListByTid(int tid);

    int insertMes(Messages messages);

    int selectfloorByTid(int tid);

    int selectAllFloorByTid(int tid);

    List<Messages> getMessagesList();
}
