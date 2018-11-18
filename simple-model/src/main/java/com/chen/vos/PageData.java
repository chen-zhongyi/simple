package com.chen.vos;

import java.util.List;

public class PageData<T extends Data> extends Data {

    private int page;
    private int size;
    private int totalPage;
    private int totalSize;
    public List<T> array;

    public PageData() {}

    public PageData(List<T> array) {
        this.page = 1;
        this.size = array.size();
        this.totalPage = this.page;
        this.totalSize = this.size;
        this.array = array;
    }

    public PageData(int page, int size, int totalSize, List<T> array) {
        this.page = page;
        this.size = size;
        this.totalPage = (totalSize - 1) / size + 1;
        this.totalSize = totalSize;
        this.array = array;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public List<T> getArray() {
        return array;
    }

    public void setArray(List<T> array) {
        this.array = array;
    }
}
