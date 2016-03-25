package com.soustock.stockquote.dao;

import com.soustock.stockquote.mapper.DayQuoteMapper;
import com.soustock.stockquote.povo.StockQuoteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/26.
 */
@Repository("dayQuoteDao")
public class DayQuoteDaoImpl implements DayQuoteDao {

    @Autowired
    private DayQuoteMapper dayQuoteMapper;

    @Override
    public String getMaxDateOfStock(String stockCode) {
        return dayQuoteMapper.getMaxDateOfStock(stockCode);
    }

    @Override
    public void insertDayQuotes(List<StockQuoteVo> stockQuoteVos) {
        dayQuoteMapper.insertDayQuotes(stockQuoteVos);
    }
}
