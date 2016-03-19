package com.soustock.stockquote.crawl.cache;


import com.soustock.stockquote.dao.StockInfoDao;
import com.soustock.stockquote.povo.Constants;
import com.soustock.stockquote.povo.StockInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xuyufei on 2016/03/07.
 */
@Component
public class StockCodeCache {

    @Autowired
    private StockInfoDao stockInfoDao;

    private Lock lock = new ReentrantLock();

    private List<String> stockCodes = null;
    public List<String> getStockCodes() {
        if (stockCodes==null) {
            lock.lock();
            try {
                if (stockCodes==null) {
                    stockCodes = fetchStockCodes();
                }
            } finally {
                lock.unlock();
            }
        }
        return stockCodes;
    }

    private List<String> fetchStockCodes() {
        List<String> stockCodeList = new ArrayList<>();
        for (String marketName : Constants.MARKET_NAME_ARR) {
            List<StockInfoVo> stockInfoVos = stockInfoDao.getAllStockInfosOfMarket(marketName);
            for (StockInfoVo vo: stockInfoVos) {
                stockCodeList.add(vo.getStockCode());
            }
        }
        return stockCodeList;
    }

    public void reset(){
        if (stockCodes!=null) {
            lock.lock();
            try {
                if (stockCodes!=null) {
                    stockCodes = null;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
