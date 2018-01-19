package cache;

import java.io.Serializable;
import java.util.Date;

/**
 * <pre>
 *
 * 【标题】: 圈子vo
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-12-14 11:16
 * </pre>
 */
public class GroupBaseVo implements Serializable{

    /** @Fields serialVersionUID: */

    private static final long serialVersionUID = 1L;

    /**
     * 圈子id
     */
    private Long id;

    /**
     * 圈子创建者Id
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 圈子名称
     */
    private String title;

    /**
     * 圈子简介
     */
    private String brief;

    /**
     * 圈子状态：1启用0停用
     */
    private Integer status;

    /**
     * 标签id集合，用","分隔
     */
    private String tagIds;
    /**
     * 圈子头像路径
     */
    private String faceUrl;

    /**
     * 加入圈子收费
     */
    private Integer cost;

    /**
     * 圈子收费类型：0一次性收费1按月收费2按季度收费3按年收费
     */
    private Integer chargeType;

    /**
     * 聊天室id
     */
    private Long chatId;

    /**
     * 发布文章和短文的规则，0仅圈主和嘉宾可发1所有人可发
     */
    private Integer articleRule;

    /**
     * 圈子是否免费：1是0否
     */
    private Integer isFree;

    /**
     * 入圈规则，仅免费圈子时有效。0自由入圈1审核入圈
     */
    private Integer joinRule;

    /*************以下为非数据库字段*****************/
    /**
     * 创建者昵称
     */
    private String createUserNickName;

    /**
     * 总人数
     */
    private Long totalMember;

    /**
     * 总内容数
     */
    private Long totalContent;


    /**
     * 直播间id
     */
    private String liveCid;

    /**
     * 成员类型，0圈主1成员2嘉宾
     * 在圈子中的成员类型
     */
    private Integer memberType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getLiveCid() {
        return liveCid;
    }

    public void setLiveCid(String liveCid) {
        this.liveCid = liveCid;
    }

    public Long getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(Long totalMember) {
        this.totalMember = totalMember;
    }

    public Long getTotalContent() {
        return totalContent;
    }

    public void setTotalContent(Long totalContent) {
        this.totalContent = totalContent;
    }

    public Integer getJoinRule() {
        return joinRule;
    }

    public void setJoinRule(Integer joinRule) {
        this.joinRule = joinRule;
    }

    public String getCreateUserNickName() {
        return createUserNickName;
    }

    public void setCreateUserNickName(String createUserNickName) {
        this.createUserNickName = createUserNickName;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFaceUrl() {
        return faceUrl;
    }

    public Integer getMemberType() {
        return memberType;
    }

    public void setMemberType(Integer memberType) {
        this.memberType = memberType;
    }

    public void setFaceUrl(String faceUrl) {
        this.faceUrl = faceUrl;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getChargeType() {
        return chargeType;
    }

    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getArticleRule() {
        return articleRule;
    }

    public void setArticleRule(Integer articleRule) {
        this.articleRule = articleRule;
    }


}
