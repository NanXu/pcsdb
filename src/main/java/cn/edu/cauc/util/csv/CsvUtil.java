package cn.edu.cauc.util.csv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import cn.edu.cauc.model.po.data.AidsRawData;

/**
 * 作者： 徐楠
 *
 * 描述：<p>csv 文件读取工具类</p>
 * 创建时间：2016年9月19日
 */
public class CsvUtil {
	
//	//导出的英文日期格式
//	private final String ENLISH_DATE_FORMAT = "dd-MMM-yy";
//	
//	private final String LOCAL_DATE_FORMAT = "yyyy-MM-dd";
	

	/**
	 * 读取AIDS 数据源
	 * 
	 * @param pathname
	 * 			csv路径
	 * @return
	 * @throws IOException 
	 */
	public static List<AidsRawData> readAIDSRawDataFromCsv(String pathname) throws IOException {
		List<AidsRawData> rawDataList = new ArrayList<AidsRawData>();
		File csvFile = new File(pathname);
		CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvFile)));
		String[] nextLine;
		int index = 0;
		 while ((nextLine = reader.readNext()) != null) { 
			 if(index == 0) {//首行为标题
				 index ++;
				 continue;
			 }
         	AidsRawData rawData = new AidsRawData();
         	rawData.setReportNumber(nextLine[0]);
         	rawData.setLocalEventDate(nextLine[1]);
         	rawData.setEventCity(nextLine[2]);
         	rawData.setEventState(nextLine[3]);
         	rawData.setEventAirport(nextLine[4]);
         	rawData.setEventType(nextLine[5]);
         	rawData.setAircraftDamage(nextLine[6]);
         	rawData.setFlightPhase(nextLine[7]);
         	rawData.setAircraftMake(nextLine[8]);
         	rawData.setAircraftModel(nextLine[9]);
         	rawData.setAircraftSeries(nextLine[10]);
         	rawData.setOperator(nextLine[11]);
         	rawData.setPrimaryFlightType(nextLine[12]);
         	rawData.setFlightConductCode(nextLine[13]);
         	rawData.setFlightPlanFiledCode(nextLine[14]);
         	rawData.setAircraftRegistrationNbr(nextLine[15]);
         	rawData.setTotalFatalities(nextLine[16]);
         	rawData.setTotalInjuries(nextLine[17]);
         	rawData.setAircraftEngineMake(nextLine[18]);
         	rawData.setAircraftEngineModel(nextLine[19]);
         	rawData.setEngineGroupCode(nextLine[20]);
         	rawData.setNbrofEngines(nextLine[21]);
         	rawData.setPicCertificateType(nextLine[22]);
         	rawData.setPicFlightTimeTotalHrs(nextLine[23]);
         	rawData.setPicFlightTimeTotalMakeModel(nextLine[24]);
         	rawData.setPicFlightTime90DTotalTime(nextLine[25]);
         	rawData.setPicFlightTime90DTotalMakeModel(nextLine[26]);
         	
         	rawDataList.add(rawData);
         }  
		 reader.close();
		return rawDataList;
	}
	
	public static void main(String[] args) throws Exception {
		//13-Feb-12
//		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy", Locale.ENGLISH);
//		Date date = sdf.parse("13-Feb-12");
//		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
//		System.out.println(sdf1.format(date));
		CsvUtil csvUtil = new CsvUtil();
		String pathname = "E:/work/cauc.edu/data/AIDS_REPORTS.csv";
		try {
			List<AidsRawData> rawDataList = csvUtil.readAIDSRawDataFromCsv(pathname);
			System.out.println(rawDataList);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
