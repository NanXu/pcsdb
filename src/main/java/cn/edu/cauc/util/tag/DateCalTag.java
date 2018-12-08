package cn.edu.cauc.util.tag;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import cn.edu.cauc.util.DateUtil;

/**
 * 作者： 徐楠
 *
 * 描述：<p>自定义时间计算标签</>
 * 创建时间：2016年2月26日
 */
public class DateCalTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182605499478539392L;
	
	private String strDate;	//时间字符串
	private String months;	//相加的月份数

	@Override
	public int doStartTag() {
		JspWriter out = this.pageContext.getOut();  
        
        try {
        	String result = DateUtil.getAfterMonthDate(strDate, months);
            out.print(result);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return EVAL_BODY_INCLUDE;  
	}

	public String getStrDate() {
		return strDate;
	}

	public void setStrDate(String strDate) {
		this.strDate = strDate;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

}
