package cn.bd.dailyReport.utils.pages;

import java.util.List;

/**
 * Created by 赵传志 on 2018/1/26.
 */
public class Page<T> {

    private List<T> list;

    private int totalPages;

    private int totalElements;

    public Page() {
    }

    public Page(List<T> list, int totalNumber) {
        this.list = list;
        this.totalElements = totalNumber;
    }

    public Page(List<T> list, int totalPages, int totalElements) {
        this.list = list;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }


}
