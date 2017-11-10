package com.rt.druid.dto;

/**
 * <pre>
 *
 * 【标题】: 行情数据类型为forexdata类型的基本展示数据dto
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-07-12 16:10
 * </pre>
 */
public class MarketRealForexdataDto{
    private static final long serialVersionUID = -9154540031412021865L;
    /**
     * 主键(如果分库分表，则无法使用自增)
     */
    private Long id;

    /**
     * 品种代码
     */
    private String prodCode;

    /**
     * 品种名称
     */
    private String prodName;

    /**
     * 最近一次的价格
     */
    private String lastPx;

    /**
     * 价格变化量
     */
    private String pxChange;

    /**
     * 价格变化率
     */
    private String pxChangeRate;

    /**
     * 最高价
     */
    private String highPx;

    /**
     * 最低价
     */
    private String lowPx;

    /**
     * 今天开盘价
     */
    private String openPx;

    /**
     * 昨天收盘价
     */
    private String preclosePx;

    /**
     * 成交量
     */
    private String businessAmount;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 证券类型
     */
    private String securitiesType;

    /**
     * 更新时间，毫秒数
     */
    private Long updateTime;

    /**
     * 精度,这里是毫秒数
     */
    private Integer pricePrecision;

    /**
     * 52周最高
     */
    private String week52High;

    /**
     * 52周最低
     */
    private String week52Low;

    /**
     * 持仓或保留
     */
    private String fopenReserve;

    /**
     * 结算价或保留
     */
    private String fcloseReserve;

    /**
     * 前收盘价
     */
    private String lastClosePx;

    /**
     * 成交价格总额
     */
    private String businessTotalPx;

    /**
     * 主键(如果分库分表，则无法使用自增)
     **/
    public Long getId() {
        return id;
    }

    /**
     * 主键(如果分库分表，则无法使用自增)
     **/
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 品种代码
     **/
    public String getProdCode() {
        return prodCode;
    }

    /**
     * 品种代码
     **/
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode == null ? null : prodCode.trim();
    }

    /**
     * 品种名称
     **/
    public String getProdName() {
        return prodName;
    }

    /**
     * 品种名称
     **/
    public void setProdName(String prodName) {
        this.prodName = prodName == null ? null : prodName.trim();
    }

    /**
     * 最近一次的价格
     **/
    public String getLastPx() {
        return lastPx;
    }

    /**
     * 最近一次的价格
     **/
    public void setLastPx(String lastPx) {
        this.lastPx = lastPx == null ? null : lastPx.trim();
    }

    /**
     * 价格变化量
     **/
    public String getPxChange() {
        return pxChange;
    }

    /**
     * 价格变化量
     **/
    public void setPxChange(String pxChange) {
        this.pxChange = pxChange == null ? null : pxChange.trim();
    }

    /**
     * 价格变化率
     **/
    public String getPxChangeRate() {
        return pxChangeRate;
    }

    /**
     * 价格变化率
     **/
    public void setPxChangeRate(String pxChangeRate) {
        this.pxChangeRate = pxChangeRate == null ? null : pxChangeRate.trim();
    }

    /**
     * 最高价
     **/
    public String getHighPx() {
        return highPx;
    }

    /**
     * 最高价
     **/
    public void setHighPx(String highPx) {
        this.highPx = highPx == null ? null : highPx.trim();
    }

    /**
     * 最低价
     **/
    public String getLowPx() {
        return lowPx;
    }

    /**
     * 最低价
     **/
    public void setLowPx(String lowPx) {
        this.lowPx = lowPx == null ? null : lowPx.trim();
    }

    /**
     * 今天开盘价
     **/
    public String getOpenPx() {
        return openPx;
    }

    /**
     * 今天开盘价
     **/
    public void setOpenPx(String openPx) {
        this.openPx = openPx == null ? null : openPx.trim();
    }

    /**
     * 昨天收盘价
     **/
    public String getPreclosePx() {
        return preclosePx;
    }

    /**
     * 昨天收盘价
     **/
    public void setPreclosePx(String preclosePx) {
        this.preclosePx = preclosePx == null ? null : preclosePx.trim();
    }

    /**
     * 成交量
     **/
    public String getBusinessAmount() {
        return businessAmount;
    }

    /**
     * 成交量
     **/
    public void setBusinessAmount(String businessAmount) {
        this.businessAmount = businessAmount == null ? null : businessAmount.trim();
    }

    /**
     * 交易状态
     **/
    public String getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 交易状态
     **/
    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus == null ? null : tradeStatus.trim();
    }

    /**
     * 证券类型
     **/
    public String getSecuritiesType() {
        return securitiesType;
    }

    /**
     * 证券类型
     **/
    public void setSecuritiesType(String securitiesType) {
        this.securitiesType = securitiesType == null ? null : securitiesType.trim();
    }

    /**
     * 更新时间，毫秒数
     **/
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间，毫秒数
     **/
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 精度,这里是毫秒数
     **/
    public Integer getPricePrecision() {
        return pricePrecision;
    }

    /**
     * 精度,这里是毫秒数
     **/
    public void setPricePrecision(Integer pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    /**
     * 52周最高
     **/
    public String getWeek52High() {
        return week52High;
    }

    /**
     * 52周最高
     **/
    public void setWeek52High(String week52High) {
        this.week52High = week52High == null ? null : week52High.trim();
    }

    /**
     * 52周最低
     **/
    public String getWeek52Low() {
        return week52Low;
    }

    /**
     * 52周最低
     **/
    public void setWeek52Low(String week52Low) {
        this.week52Low = week52Low == null ? null : week52Low.trim();
    }

    /**
     * 持仓或保留
     */
    public String getFopenReserve() {
        return fopenReserve;
    }

    /**
     * 持仓或保留
     */
    public void setFopenReserve(String fopenReserve) {
        this.fopenReserve = fopenReserve;
    }

    /**
     * 结算价或保留
     */
    public String getFcloseReserve() {
        return fcloseReserve;
    }

    /**
     * 结算价或保留
     */
    public void setFcloseReserve(String fcloseReserve) {
        this.fcloseReserve = fcloseReserve;
    }

    /**
     * 前收盘价
     */
    public String getLastClosePx() {
        return lastClosePx;
    }

    /**
     * 前收盘价
     */
    public void setLastClosePx(String lastClosePx) {
        this.lastClosePx = lastClosePx;
    }

    /**
     * 成交价格总额
     */
    public String getBusinessTotalPx() {
        return businessTotalPx;
    }

    /**
     * 成交价格总额
     */
    public void setBusinessTotalPx(String businessTotalPx) {
        this.businessTotalPx = businessTotalPx;
    }
}