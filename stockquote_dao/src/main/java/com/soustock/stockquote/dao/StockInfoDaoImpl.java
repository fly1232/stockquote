package com.soustock.stockquote.dao;

import com.soustock.stockquote.povo.StockInfoVo;
import com.soustock.stockquote.utils.PageCdt;
import com.soustock.stockquote.utils.PageList;
import com.soustock.stockquote.mapper.StockInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/6.
 */
@Repository("stockInfoDao")
public class StockInfoDaoImpl implements StockInfoDao {

    @Autowired
    private StockInfoMapper stockInfoMapper;


    @Override
    public StockInfoVo getStockInfoByStockCode(String stockCode) {
        return stockInfoMapper.getStockInfoByStockCode(stockCode);
    }

    @Override
    public void insertStockInfo(StockInfoVo stockInfo) {
        stockInfoMapper.insertStockInfo(stockInfo);
    }

    /**
     * 修改一条股票信息
     * @param stockInfo
     */
    public void updateStockInfo(StockInfoVo stockInfo){
        stockInfoMapper.updateStockInfo(stockInfo);
    }

    /**
     * 获取一个市场中的股票数目
     * @param market
     * @return
     */
    public long getStockInfoCountOfMarket(String market){
        return stockInfoMapper.getStockInfoCountOfMarket(market);
    }

//    @Override
//    public PageList<StockInfoVo> getStockInfosOfMarket(String market, PageCdt pageCdt) {
//        long total = stockInfoMapper.getStockInfoCountOfMarket(market);
//        int pageSize = (int)Math.ceil((total * 1.0) / pageCdt.getPageSize());
//        int startRow = (pageCdt.getPageNum()-1)* pageCdt.getPageSize();
//        List<StockInfoVo> stockInfoVos = stockInfoMapper.getStockInfosOfMarket(market,
//                startRow, pageCdt.getPageSize());
//        PageList<StockInfoVo> result = new PageList<>();
//        result.setPageCdt(pageCdt);
//        result.setPages(pageSize);
//        result.setResult(stockInfoVos);
//        result.setTotal(total);
//        return result;
//    }

    @Override
    public List<StockInfoVo> getStockInfosOfLikeStr(String likeStr) {
        return stockInfoMapper.getStockInfosOfLikeStr(likeStr);
    }

    @Override
    public List<StockInfoVo> getAllStockInfosOfMarket(String market) {
        return stockInfoMapper.getAllStockInfosOfMarket(market);
    }
}
