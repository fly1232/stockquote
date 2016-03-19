package com.soustock.stockquote.crawl.task;

import com.soustock.stockquote.crawl.cache.StockCodeCache;
import com.soustock.stockquote.crawl.common.BaseCrawlTask;
import com.soustock.stockquote.dao.DayQuoteDao;
import com.soustock.stockquote.dao.StockInfoDao;
import com.soustock.stockquote.exception.BusinessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xuyufei on 2015/9/19.
 * 股票行情抓取
 */
//@Component
public class DayQuoteCrawlTask extends BaseCrawlTask {

    private final static Log logger = LogFactory.getLog(DayQuoteCrawlTask.class);

    @Autowired
    private StockCodeCache stockCodeCache;

    @Autowired
    private DayQuoteDao dayQuoteDao;

    @Autowired
    private StockInfoDao stockInfoDao;

    @Override
    protected void process() throws BusinessException {
        try {
            List<String> stockCodes = stockCodeCache.getStockCodes();
            int len = stockCodes.size();
            ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
//            BlockingQueue<Runnable> secuCodeQueue = new LinkedBlockingQueue<Runnable>(100);
//            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 60, TimeUnit.SECONDS, secuCodeQueue);
//            threadPoolExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
//                @Override
//                public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
//                    logger.info("SecuCode Rejected : " + ((StockQuoteCrawlUnitTask) r).getStockCode());
//                    try {
//                        boolean bEnter = false;
//                        while (!bEnter) {
//                            logger.info("Waiting for 3000 ms !!");
//                            Thread.sleep(3000);
//                            bEnter = secuCodeQueue.offer(r);
//                        }
//                        logger.info("SecuCode accepted : " + ((StockQuoteCrawlUnitTask) r).getStockCode());
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            //Let start all core threads initially
//            threadPoolExecutor.prestartAllCoreThreads();

            AtomicInteger rowIndex = new AtomicInteger(0);
            int stockCount = stockCodes.size();
            for (String stockCode: stockCodes) {
                DayQuoteCrawlUnitTask unitTask = new DayQuoteCrawlUnitTask(stockCode);
                unitTask.setDayQuoteDao(dayQuoteDao);
                unitTask.setStockInfoDao(stockInfoDao);
                unitTask.setRowIndex(rowIndex);
                unitTask.setStockCount(stockCount);
                executorService.execute(unitTask);
            }

            while (executorService.getActiveCount()> 0 ){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            executorService.shutdown();
        }
        catch (Exception ex){
            throw new BusinessException(ex);
        }
    }

    @Override
    public String getTaskName() {
        return "日行情抓取";
    }

    @Override
    public int getExecuteOrder() {
        return 1;
    }

}
