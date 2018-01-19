package com.rt.druid.vo;

/**
 * <pre>
 *
 * 【标题】: 行情返回数据的vo
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-02 19:05
 * </pre>
 */
public class MarketRespVo {

    private float lowPx;//最低价

    private float highPx;//最高价

    private float openPx;//开盘价

    private float closePx;//收盘价

    private Long timestamp;//时间戳

    private String prodCode;//行情编码

    public MarketRespVo() {
    }

    public MarketRespVo(float lowPx, float highPx, float openPx, float closePx, Long timestamp,String prodCode) {
        this.lowPx = lowPx;
        this.highPx = highPx;
        this.openPx = openPx;
        this.closePx = closePx;
        this.timestamp = timestamp;
        this.prodCode = prodCode;
    }

    public float getLowPx() {
        return lowPx;
    }

    public void setLowPx(float lowPx) {
        this.lowPx = lowPx;
    }

    public float getHighPx() {
        return highPx;
    }

    public void setHighPx(float highPx) {
        this.highPx = highPx;
    }

    public float getOpenPx() {
        return openPx;
    }

    public void setOpenPx(float openPx) {
        this.openPx = openPx;
    }

    public float getClosePx() {
        return closePx;
    }

    public void setClosePx(float closePx) {
        this.closePx = closePx;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }
}
