package cn.edu.cauc.dao.event;

import cn.edu.cauc.dao.base.IBaseDao;
import cn.edu.cauc.model.po.event.AidsCsvData;

import java.util.List;

public interface IAidsCsvDataDao extends IBaseDao<AidsCsvData> {

    public List<AidsCsvData> findAllBySQL();
}
