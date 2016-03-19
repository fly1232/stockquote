package com.soustock.stockquote.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuyufei on 2016/3/6.
 * 分页查询结果列表
 */
public class PageList<E> implements Serializable{

    /**
     * 分页条件
     */
    private PageCdt pageCdt;

    /**
     * 总行数
     */
    private long total;

    /**
     * 总页数
     */
    private int pages;

    /**
     * 当前页的数据
     */
    private List<E> result;

    public PageCdt getPageCdt() {
        return pageCdt;
    }

    public void setPageCdt(PageCdt pageCdt) {
        this.pageCdt = pageCdt;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<E> getResult() {
        return result;
    }

    public void setResult(List<E> result) {
        this.result = result;
    }

}
