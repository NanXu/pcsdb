package cn.edu.cauc.model.vo;

import java.io.Serializable;

/**
 * @author: Nan.Xu
 * @create: 2018-12-09
 **/
public class KeywordsStatView implements Serializable {


    private static final long serialVersionUID = -9115284522388671388L;

    private String keywords;
    private String startDate;
    private String endDate;
    private long total;
    private String source;

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
