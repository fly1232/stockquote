package com.soustock.stockquote.dao;

import com.soustock.stockquote.povo.DayQuoteCdtVo;
import com.soustock.stockquote.povo.StockQuoteVo;
import com.soustock.stockquote.povo.PageList;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/5.
 * 股票行情的dao
 */
public interface DayQuoteDao {

    /**
     * 获取某个股票的行情最大日期(yyyyMMdd)
     */
    String getMaxDateOfStock(String stockCode);

//    /**
//     * 根据股票代码、起止日期查找股票行情
//     * @param stockCode
//     * @param beginDate
//     * @param endDate
//     * @return
//     */
//    List<StockQuoteVo> getStockQuoteBetween(String stockCode, String beginDate, String endDate);

    void insertDayQuotes(List<StockQuoteVo> stockQuoteVos);

    PageList<StockQuoteVo> getStockQuoteByStockCode(DayQuoteCdtVo dayQuoteCdtVo);

}
