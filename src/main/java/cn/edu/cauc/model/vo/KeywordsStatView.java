package cn.edu.cauc.model.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Nan.Xu
 * @create: 2018-12-09
 **/
public class KeywordsStatView implements Serializable {


    private static final long serialVersionUID = -9115284522388671388L;

    private String keyword;
    private String startDate;
    private String endDate;
    private long total;
    private String source;
    private String flightProperties;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFlightProperties() {
        return flightProperties;
    }

    public void setFlightProperties(String flightProperties) {
        this.flightProperties = flightProperties;
    }
}
