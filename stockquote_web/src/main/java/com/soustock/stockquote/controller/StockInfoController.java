package com.soustock.stockquote.controller;

import com.soustock.stockquote.dao.StockInfoDao;
import com.soustock.stockquote.povo.StockInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xuyufei on 2016/3/26.
 */
@Controller
@RequestMapping("/stockInfo")
public class StockInfoController {

    @Autowired
    private StockInfoDao stockInfoDao;

    @RequestMapping(value = "/getStockInfosOfLikeStr", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStockInfosOfLikeStr(HttpServletRequest request) throws Exception {
        String likeStr = request.getParameter("likeStr");
        List<StockInfoVo> stockInfoVos = stockInfoDao.getStockInfosOfLikeStr(likeStr);

        Map<String, Object> map = new HashMap<>();
        map.put("isSucc", "true");
        map.put("result", stockInfoVos);
        return map;
    }

    @RequestMapping(value = "/getStockInfoByStockCode", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getStockInfoByStockCode(HttpServletRequest request) throws Exception {
        String stockCode = request.getParameter("stockCode");
        StockInfoVo stockInfoVo = stockInfoDao.getStockInfoByStockCode(stockCode);

        Map<String, Object> map = new HashMap<>();
        map.put("isSucc", "true");
        map.put("result", stockInfoVo);
        return map;
    }
}
