package com.cheer.bbs.service.impl;

import com.cheer.bbs.dao.TitleDao;
import com.cheer.bbs.pojo.Titles;
import com.cheer.bbs.service.TitService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Service
public class TitServiceImpl implements TitService {
    @Resource
    private TitleDao titleDao;

    @Override
    public List<Titles> getTitList() {
        List<Titles> titList = this.titleDao.getTitList();
        return titList;
    }

    @Override
    public Titles getTitById(int id) {
        Titles tit = this.titleDao.getTitById(id);
        return tit;
    }

    @Override
    public int insertTitles(Titles tit) {
        int i = this.titleDao.insertTitles(tit);
        if(i>0){
            System.out.println("插入成功！");
            return i;
        }else {
            return i;
        }
    }

    @Override
    public int update(Titles tit) {
        int update = this.titleDao.update(tit);
        if(update>0){
            System.out.println("插入成功");
            return update;
        }else {
            System.out.println("插入失败");
            return update;
        }
    }

    @Override
    public int delete(int id) {
        int i = this.titleDao.delete(id);
        return i;
    }

    @Override
    public int selectNewTitle() {
        int tid = this.titleDao.selectNewTitle();
        return tid;
    }

    @Override
    public List<Titles> getTitleListByCname(String cname) {
        List<Titles> name2List = this.titleDao.getTitleListByCname(cname);
        return name2List;
    }

    //查询帖子对应的回复数
    @Override
    public int selectcommentNumByid(int id) {
       return titleDao.selectcommentNumByid ( id );
    }


    //搜索帖子功能
    @Override
    public List<Titles> searchTitles(String searchContext) {
       return titleDao.searchTitles ( searchContext);
    }
}
