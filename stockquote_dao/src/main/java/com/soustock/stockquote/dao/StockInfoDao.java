package com.soustock.stockquote.dao;

import com.soustock.stockquote.povo.StockInfoVo;
import com.soustock.stockquote.utils.PageCdt;
import com.soustock.stockquote.utils.PageList;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/5.
 * 读写股票基本信息的dao
 */
public interface StockInfoDao {

    /**
     * 根据股票代码获取股票的基本信息
     * @param stockCode
     * @return
     */
    StockInfoVo getStockInfoByStockCode(String stockCode);

    /**
     * 写入一条股票信息
     * @param stockInfo
     */
    void insertStockInfo(StockInfoVo stockInfo);

    /**
     * 修改一条股票信息
     * @param stockInfo
     */
    void updateStockInfo(StockInfoVo stockInfo);

    /**
     * 获取一个市场中的股票数目
     * @param market
     * @return
     */
    long getStockInfoCountOfMarket(String market);


    /**
     * 获取某个市场的所有股票信息，分页处理
     * @param market 市场代码
     * @param pageCdt 分页条件
     * @return
     */
//    PageList<StockInfoVo> getStockInfosOfMarket(String market, PageCdt pageCdt);

    /**
     * 根据股票代码或名称或拼音，查询匹配的股票列表（最多20条）
     * @param likeStr
     * @return
     */
    List<StockInfoVo> getStockInfosOfLikeStr(String likeStr);

    /**
     * 获得某个市场所有的股票信息
     * @param market
     * @return
     */
    List<StockInfoVo> getAllStockInfosOfMarket(String market);
}
