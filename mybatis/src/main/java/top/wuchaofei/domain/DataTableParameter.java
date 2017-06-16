package top.wuchaofei.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wuchaofei on 2017/4/7.
 */
public class DataTableParameter implements Serializable{
    private static final long serialVersionUID = 1L;

    /*$sEcho = XUtil::xget('sEcho'); // DataTables 用来生成的信息
    $start = XUtil::xget('iDisplayStart'); //显示的起始索引
    $length = XUtil::xget('iDisplayLength');//显示的行数
    $sort_th = XUtil::xget('iSortCol_0');//被排序的列
    $sort_type = XUtil::xget('sSortDir_0');//排序的方向 "desc" 或者 "asc".
    $search = XUtil::xget('sSearch');//全局搜索字段*/

    private String sEcho;

    private Long iTotalRecords;

    private Long iTotalDisplayRecords;

    private List<?> aaData;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getsEcho() {
        return sEcho;
    }

    public void setsEcho(String sEcho) {
        this.sEcho = sEcho;
    }

    public Long getiTotalRecords() {
        return iTotalRecords;
    }

    public void setiTotalRecords(Long iTotalRecords) {
        this.iTotalRecords = iTotalRecords;
    }

    public Long getiTotalDisplayRecords() {
        return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(Long iTotalDisplayRecords) {
        this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public List<?> getAaData() {
        return aaData;
    }

    public void setAaData(List<?> aaData) {
        this.aaData = aaData;
    }
}
