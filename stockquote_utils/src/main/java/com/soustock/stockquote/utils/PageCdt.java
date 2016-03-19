package com.soustock.stockquote.utils;

import java.io.Serializable;

/**
 * Created by xuyufei on 2016/3/6.
 * 分页查询条件
 */
public class PageCdt implements Serializable {

    /**
     * 第几页(从1开始计数)
     */
    private int pageNum = 1;

    /**
     * 每页条数
     */
    private int pageSize = 20;

    /**
     * 排序字段名
     */
    private String sortFieldName;

    /**
     * 是否升序
     */
    private boolean isAsc = true;


    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortFieldName() {
        return sortFieldName;
    }

    public void setSortFieldName(String sortFieldName) {
        this.sortFieldName = sortFieldName;
    }

    public boolean isAsc() {
        return isAsc;
    }

    public void setIsAsc(boolean isAsc) {
        this.isAsc = isAsc;
    }
}
