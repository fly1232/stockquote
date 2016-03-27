package com.soustock.stockquote.controller;

import com.soustock.stockquote.dao.DayQuoteDao;
import com.soustock.stockquote.povo.DayQuoteCdtVo;
import com.soustock.stockquote.povo.PageList;
import com.soustock.stockquote.povo.StockQuoteVo;
import com.soustock.stockquote.utils.AutoPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyufei on 2016/3/26.
 */
@Controller
@RequestMapping("/dayQuote")
public class DayQuoteController {

    @Autowired
    private DayQuoteDao dayQuoteDao;

    @RequestMapping(value = "/initQuoteView", method = RequestMethod.GET)
    public String initView(Model model) throws Exception {
        DayQuoteCdtVo cdtVo = new DayQuoteCdtVo();
        cdtVo.setStockCode("SH600000");
        cdtVo.setPageNum(1);
        cdtVo.setPageSize(10);
        model.addAttribute("paramVo", cdtVo);
        return "dayQuote/dayQuoteList";
    }

    @RequestMapping(value = "/listQuoteView", method = RequestMethod.POST)
    public String listView(HttpServletRequest request, Model model) throws Exception {
        DayQuoteCdtVo  dayQuoteCdtVo = AutoPojo.bindRequestParam(request, DayQuoteCdtVo.class);
        dayQuoteCdtVo.setIsTradeDateAsc(false);
        PageList<StockQuoteVo> workNodeStateVoPageList = dayQuoteDao.getStockQuoteByStockCode(dayQuoteCdtVo);

        Map<String, Object> map = new HashMap<>();
        map.put("paramVo", dayQuoteCdtVo);
        map.put("list", workNodeStateVoPageList.getList());
        map.put("totalPages", workNodeStateVoPageList.getTotalPages());
        map.put("totalRows", workNodeStateVoPageList.getTotalRows());
        map.put("pageNum", dayQuoteCdtVo.getPageNum());
        map.put("pageSize", dayQuoteCdtVo.getPageSize());
        model.addAllAttributes(map);
        return "dayQuote/dayQuoteList";
    }

    @RequestMapping(value = "/queryQuoteData", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryQuoteData(HttpServletRequest request) throws Exception {
        DayQuoteCdtVo  dayQuoteCdtVo = AutoPojo.bindRequestParam(request, DayQuoteCdtVo.class);
        PageList<StockQuoteVo> workNodeStateVoPageList = dayQuoteDao.getStockQuoteByStockCode(dayQuoteCdtVo);

        Map<String, Object> map = new HashMap<>();
        map.put("paramVo", dayQuoteCdtVo);
        map.put("list", workNodeStateVoPageList.getList());
        map.put("totalPages", workNodeStateVoPageList.getTotalPages());
        map.put("totalRows", workNodeStateVoPageList.getTotalRows());
        map.put("pageNum", dayQuoteCdtVo.getPageNum());
        map.put("pageSize", dayQuoteCdtVo.getPageSize());
        return map;
    }
}
