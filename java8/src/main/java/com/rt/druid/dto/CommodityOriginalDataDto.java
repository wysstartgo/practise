package com.rt.druid.dto;

/**
 * <pre>
 *
 * 【标题】: 商品原始数据dto
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-08 10:37
 * </pre>
 */
public class CommodityOriginalDataDto {

    private long dealTime;// 成交时间

    private String prodCode;// 品种代码

    private String prodName;// 品种名称

    private float newPrice;// 最新价

    private float lastestAmount;// 最近一次成交量

    private float openInt;// 持仓量

    private float open;// 开盘价

    private float lastClose;// 昨收价

    private float high;// 当日最高价

    private float low;// 当日最低价

    private float price3;// 前一交易日结算价

    public CommodityOriginalDataDto(long dealTime, String prodCode, String prodName, float newPrice,
                                    float lastestAmount, float openInt, float open, float lastClose, float high, float low, float price3) {
        this.dealTime = dealTime;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.newPrice = newPrice;
        this.lastestAmount = lastestAmount;
        this.openInt = openInt;
        this.open = open;
        this.lastClose = lastClose;
        this.high = high;
        this.low = low;
        this.price3 = price3;
    }

    public CommodityOriginalDataDto() {
    }

    public long getDealTime() {
        return dealTime;
    }

    public void setDealTime(long dealTime) {
        this.dealTime = dealTime;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public float getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(float newPrice) {
        this.newPrice = newPrice;
    }

    public float getLastestAmount() {
        return lastestAmount;
    }

    public void setLastestAmount(float lastestAmount) {
        this.lastestAmount = lastestAmount;
    }

    public float getOpenInt() {
        return openInt;
    }

    public void setOpenInt(float openInt) {
        this.openInt = openInt;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getLastClose() {
        return lastClose;
    }

    public void setLastClose(float lastClose) {
        this.lastClose = lastClose;
    }

    public float getHigh() {
        return high;
    }

    public void setHigh(float high) {
        this.high = high;
    }

    public float getLow() {
        return low;
    }

    public void setLow(float low) {
        this.low = low;
    }

    public float getPrice3() {
        return price3;
    }

    public void setPrice3(float price3) {
        this.price3 = price3;
    }
}
