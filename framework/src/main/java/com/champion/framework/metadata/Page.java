package com.champion.framework.metadata;

import java.util.Collection;

/**
 * @Description: page
 * @Auther： william
 * @Date：2017/6/8 0008 13:45
 */
public class Page<T> implements java.io.Serializable{

    private static final long serialVersionUID = 7381336748128484021L;
    /**
     * 当前页码
     */
    private int pageNo;

    /**
     * 页面数据
     */
    private Collection<T> data;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }
}
