package com.soustock.stockquote.mapper;

import com.soustock.stockquote.povo.DayQuoteCdtVo;
import com.soustock.stockquote.povo.StockQuoteVo;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/26.
 * 日行情dao的mybatis实现
 */
public interface DayQuoteMapper {

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

    List<StockQuoteVo> getStockQuoteByStockCode(DayQuoteCdtVo dayQuoteCdtVo);

    int getQuoteCountOfStockCode(String stockCode);

}
