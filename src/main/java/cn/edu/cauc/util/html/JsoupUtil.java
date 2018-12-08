package cn.edu.cauc.util.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import cn.edu.cauc.util.ConfigReader;

/**
 * 作者： 徐楠
 *
 * 描述：<p>字段抓取</p>
 * 创建时间：2016年10月10日
 */
public class JsoupUtil {

//	public static String parseURL = "http://www.asias.faa.gov/pls/apex/f?p=100:18:0::NO::AP_BRIEF_RPT_VAR:20150621007989I";
	//public static String URL = "http://www.asias.faa.gov/pls/apex/f?p=100:11:0::NO:::";
	//							http://www.asias.faa.gov/pls/apex/f?p=100:11:0::NO:::
	
	/**
	 * 抓取AIDS的备注字段
	 * 
	 * @param reportNumber
	 * @return
	 * @throws Exception
	 */
	public static String captureAidsEventRemarks(String reportNumber) throws Exception {
		String remark = "";
		String url = ConfigReader.getValue("aids.remark.url");
		Document document = Jsoup.connect(url+reportNumber).timeout(6000).get();
		Elements element = document.select("div[id=narr_text]");
		remark = element.text();
		return remark;
	}
	
//	public static void main(String[] args) {
//		try {
//			Document document = Jsoup.connect(parseURL).timeout(60000).get();
////			Elements elements = document.select("table[class=uReport uReportStandard]").select("tbody").select("tr");
//			//<div id="narr_text">
//			Elements element = document.select("div[id=narr_text]");
//			System.out.println(element.text());
////			System.out.println("共：" + elements.size());
////			for(Element element : elements) {
////				Elements tds = (Elements)element.select("td");
////				for(Element td : tds) {
////					//String href = td.select("a").attr("href");
////					System.out.print(td.text() + " | ");
////				}
////				System.out.println();
////			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
}
