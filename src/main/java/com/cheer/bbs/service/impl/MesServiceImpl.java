package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.MesDao;
import com.cheer.bbs.pojo.Messages;
import com.cheer.bbs.service.MesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class MesServiceImpl implements MesService {
    @Autowired
    private MesDao mesDao;


    @Override
    public List<Messages> getMesListByTid(int tid) {
        List<Messages> mesList = this.mesDao.getMesListByTid(tid);
        return mesList;
    }

    @Override
    public int insertMes(Messages messages) {
        int i = this.mesDao.insertMes(messages);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }
    }

    @Override
    public int selectfloorByTid(int tid) {
        int i = this.mesDao.selectfloorByTid(tid);
        return i;
    }

    @Override
    public int selectAllFloorByTid(int tid) {
        int i = mesDao.selectAllFloorByTid ( tid );
        return i;

    }

    @Override
    public List<Messages> getMessagesList() {
       return mesDao.getMessagesList ();
    }
}
