package cn.edu.cauc.util.html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.HtmlPage;

/**
 * 作者： 徐楠
 *
 * 描述：<p>URL抓取工具</p>
 * 创建时间：2016年3月10日
 */
public class ReadUrl {

	public static String parseURL = "http://www.asias.faa.gov/pls/apex/f?p=100:18:0::NO::AP_BRIEF_RPT_VAR:20150621007989I";
	//public static String parseURL = "http://www.asias.faa.gov/pls/apex/f?p=100:11:0::NO:::";
	public static String getHtml(String urlString) {
		try {
			StringBuffer html = new StringBuffer();
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			InputStreamReader isr = new InputStreamReader(conn.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			String temp;
			while ((temp = br.readLine()) != null) {
				html.append(temp).append("\n");
			}
			br.close();
			isr.close();
			return html.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private static String getContent() {
		StringBean sBean = new StringBean();
		sBean.setLinks(true);
		sBean.setCollapse(true);
		sBean.setReplaceNonBreakingSpaces(true);
		sBean.setURL(parseURL);
		// System.out.println("This content is:"+sBean.getStrings());
		return sBean.getStrings();
	}

	public static void main(String[] args) {
		String htmlcode = ReadUrl.getHtml(parseURL);
		Parser parser = Parser.createParser(htmlcode, "GBK");
		
		HtmlPage page = new HtmlPage(parser);
		try {
			parser.visitAllNodesWith(page);
//			nodeList =parser.parse(lastFilter);
//			for (int i = 0; i <=nodeList.size(); i++) {
//				TableTag tag = (TableTag)nodeList.elementAt(i);
//				//System.out.println(tag.getChildrenHTML()); 
//				System.out.println(tag.getStringText()); 
//			}
			
		} catch (ParserException e1) {
			e1 = null;
		}
		// 显示标题
		// System.out.println(page.getTitle());
		// 显示文本
		System.out.println(getContent());
	}

}
