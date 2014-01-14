package com.nexusy.virgo.web.util;

/**
 * @author lan
 * @since 2013-11-21
 */
public class Page {

    private int pageNo = 1; //当前页数
    private int pageSize = Integer.MAX_VALUE; //每页大小
    private long total; //总记录数

    public Page() {
    }

    public Page(Integer pageNo, Integer pageSize) {
        if (pageNo != null && pageNo > 0) {
            this.pageNo = pageNo;
        }
        if (pageSize != null && pageSize > 0) {
            this.pageSize = pageSize;
        }
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getFirstResult() {
        return (pageNo - 1) * pageSize;
    }
}
