package cn.edu.cauc.service.job.impl;

import cn.edu.cauc.dao.data.ISdrRawDataDao;
import cn.edu.cauc.service.job.ISdrTxtJobService;
import cn.edu.cauc.util.ConfigReader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * @author: Nan.Xu
 * @create: 2018-11-25
 **/
@Service("sdrTxtJobService")
public class SdrTxtJobServiceImpl implements ISdrTxtJobService {

    private static final Logger logger = Logger.getLogger(SdrTxtJobServiceImpl.class);

    @Resource
    private ISdrRawDataDao sdrRawDataDao;

    private final String SDR_TXT_DATA_PATH = "sdr.file.load.path";

    @Override
    public void addSdrTxtData() {
        String pathname = ConfigReader.getValue(SDR_TXT_DATA_PATH);
        File dir = new File(pathname);
        if (dir.isDirectory()) {
            File[] fileList = dir.listFiles();
            for (File txt : fileList) {
                AddFileToDb(txt);
            }
        } else if (dir.isFile()){
            AddFileToDb(dir);
        }
    }

    private void AddFileToDb(File txt) {
        String fileName = txt.getName();
        LineIterator lineIterator = null;
        try {
            lineIterator = FileUtils.lineIterator(txt, "UTF-8");
            while (lineIterator.hasNext()) {//每一行
                String line = lineIterator.nextLine();
                String[] arrLine = line.split("\t");
                String table = fileName.substring(3, 7);
                logger.info("SDR TXT: "+table+" 【"+arrLine.toString()+"】 入库");
                //插入数据库
                try {
                    sdrRawDataDao.sdrTxtInsert(table, arrLine);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.info(e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            LineIterator.closeQuietly(lineIterator);
        }
    }
}
