package com.yuewen.vo;

import java.util.List;

public class ResponseBody<T> {

    private List<T> results;
    private String errorMsg;
    private boolean isSuccess;
    private int page;
    private int pageSize;
    private long totalElements;
    private int totalPages;

    public ResponseBody(List<T> results, String errorMsg, boolean isSuccess) {
        this.results = results;
        this.errorMsg = errorMsg;
        this.isSuccess = isSuccess;
        this.page = 0;
        this.pageSize = 0;
        this.totalElements = 0;
        this.totalPages = 0;
    }

    public ResponseBody(List<T> results, String errorMsg, boolean isSuccess, int page, int pageSize, long totalElements, int totalPages) {
        this.results = results;
        this.errorMsg = errorMsg;
        this.isSuccess = isSuccess;
        this.page = page;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "ResponseBody{" +
                "results=" + results +
                ", errorMsg='" + errorMsg + '\'' +
                ", isSuccess=" + isSuccess +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }
}
