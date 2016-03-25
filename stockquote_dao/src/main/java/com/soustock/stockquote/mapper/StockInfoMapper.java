package com.soustock.stockquote.mapper;

import com.soustock.stockquote.povo.StockInfoVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by xuyufei on 2016/3/6.
 * stockinfodao的mybatis实现
 */
public interface StockInfoMapper {

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
//    @Options(flushCache = true, timeout = 20000)
    void insertStockInfo(StockInfoVo stockInfo);

    /**
     * 获取某个市场的股票总数
     * @param market
     * @return
     */
    long getStockInfoCountOfMarket(String market);

    /**
     * 修改一条股票信息
     * @param stockInfo
     */
//    @Options(flushCache = true, timeout = 2000)
    void updateStockInfo(StockInfoVo stockInfo);

    /**
     * 获取某个市场的股票列表
     */
    List<StockInfoVo> getAllStockInfosOfMarket(String market);

    /**
     * 获取代码、名称、简称匹配输入字符串的股票列表,只输出前20个
     * @param likeStr
     * @return
     */
    List<StockInfoVo> getStockInfosOfLikeStr(String likeStr);

}
