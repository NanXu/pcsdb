package cn.edu.cauc.service.event.impl;

import cn.edu.cauc.dao.event.*;
import cn.edu.cauc.model.po.event.*;
import cn.edu.cauc.service.event.IAidsCsvDataService;
import cn.edu.cauc.util.DateUtil;
import cn.edu.cauc.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service("aidsCsvDataService")
public class AidsCsvDataServiceImpl implements IAidsCsvDataService {

    private static final Logger logger = Logger.getLogger(AidsCsvDataServiceImpl.class);
    @Resource
    private IAidsCsvDataDao aidsCsvDataDao;
    @Resource
    private IEventInfoDao eventInfoDao;
    @Resource
    private IPlaneDao planeDao;
    @Resource
    private IPollutantDao pollutantDao;
    @Resource
    private IMeasuresDao measuresDao;
    @Resource
    private ICasualtiesDao casualtiesDao;

    @Transactional
    @Override
    public void addEvent() {
        List<AidsCsvData> list = aidsCsvDataDao.findAllBySQL();
        if (list != null && !list.isEmpty()) {
//            for (AidsCsvData aidsCsvData : list) {
            for (int i = 0; i < list.size(); i++) {
                AidsCsvData aidsCsvData = list.get(i);
                try {
                    logger.info("The index ["+(i+1)+"] Event data start");
                    EventInfo eventInfo = new EventInfo();
                    BeanUtils.copyProperties(aidsCsvData, eventInfo);
                    String strLocalDate = aidsCsvData.getLocalDate();
                    if (!StringUtil.isNull(strLocalDate)) {
                        String[] arrLocalDate = strLocalDate.split("/");
                        if (arrLocalDate[1].length() == 1) {
                            arrLocalDate[1] = "0"+arrLocalDate[1];
                        }
                        if (arrLocalDate[2].length() == 1) {
                            arrLocalDate[2] = "0"+arrLocalDate[2];
                        }
                        Date date = null;
                        try {
                            date = DateUtil.format(arrLocalDate[0]+"-"+arrLocalDate[1]+"-"+arrLocalDate[2], "yyyy-MM-dd");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        eventInfo.setLocalDate(date);
                    }
                    eventInfoDao.add(eventInfo);
                    Plane plane = new Plane();
                    BeanUtils.copyProperties(aidsCsvData, plane);
                    plane.setEventId(eventInfo.getId());
                    planeDao.add(plane);

                    Pollutant pollutant = new Pollutant();
                    BeanUtils.copyProperties(aidsCsvData, pollutant);
                    pollutant.setSource(aidsCsvData.getPollutantSource());
                    pollutant.setEventId(eventInfo.getId());
                    pollutantDao.add(pollutant);

                    Measures measures = new Measures();
                    BeanUtils.copyProperties(aidsCsvData, measures);
                    measures.setEventId(eventInfo.getId());
                    measuresDao.add(measures);

                    Casualties casualties = new Casualties();
                    BeanUtils.copyProperties(aidsCsvData, casualties);
                    casualties.setEventId(eventInfo.getId());
                    casualtiesDao.add(casualties);
                    logger.info("The index ["+(i+1)+"] Event data end");
                } catch (Exception e) {
                    logger.debug("data [" +aidsCsvData.toString()+ "] error!");
                }

            }
        }
    }
}
