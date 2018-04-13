package com.kk.dto;

/**
 * Created by yzb on 2018/4/6.
 */
/**bootstrap只用传三个变量：
    当前页，
    总页，
    页面大小
    */
public class Page {
    //当前页码,默认第一页
    private int curentNumber = 1;
    private int upNumber=1;
    //页面容量
    private int size=5;
    //末页
    private int lastNumber;

    public int getCurentNumber() {
        return curentNumber;
    }

    public void setCurentNumber(int curentNumber) {
        this.curentNumber = curentNumber;
    }

    public int getUpNumber() {
        return upNumber;
    }

    public void setUpNumber(int upNumber) {
        this.upNumber = upNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getLastNumber() {
        return lastNumber;
    }

    public void setLastNumber(int lastNumber) {
        this.lastNumber = lastNumber;
    }
}
