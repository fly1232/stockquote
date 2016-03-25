package com.soustock.stockquote.povo;

import java.io.Serializable;

/**
 * Created by xuyufei on 2016/3/24.
 */
public class BaseDataVo implements Serializable {
    /**
     * 更新时间
     */
    private long updateTime;


    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
