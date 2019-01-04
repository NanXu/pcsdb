package cn.edu.cauc.dao.event.impl;

import cn.edu.cauc.dao.base.impl.BaseDaoImpl;
import cn.edu.cauc.dao.event.IAidsCsvDataDao;
import cn.edu.cauc.model.po.event.AidsCsvData;
import cn.edu.cauc.model.po.event.EventInfo;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("aidsCsvDataDao")
public class AidsCsvDataDaoImpl extends BaseDaoImpl<AidsCsvData> implements
        IAidsCsvDataDao {

    @Override
    public List<AidsCsvData> findAllBySQL() {
        String sql = "select * from aids_csv_data";
        Session session = this.getSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(AidsCsvData.class));;
        return query.list();
    }
}
