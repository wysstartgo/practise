package snowflake;

/**
 * <pre>
 *  
 *
 * 【标题】: 使用Twitter_Snowflake产生全局ID
 * 【描述】: 
 *   注： 每种业务要有自己的ID产生方式,以防止ID重复
 * 【版权】: 润投科技
 * 【作者】: 唐宋  
 * 【时间】: 2017年7月3日 上午10:10:13
 * </pre>
 */
public class SnowflakeIdServiceImpl implements ISnowflakeIdService {

    private SnowflakeIdServiceImpl(){}

    public static final ISnowflakeIdService INSTANCE = new SnowflakeIdServiceImpl();

    // 默认通用-全局ID产生对象
    private static volatile SnowflakeIdFactory defaultSnowflakeId = new SnowflakeIdFactory(1, 1);

    // 普通资讯-全局ID产生对象
    private static volatile SnowflakeIdFactory ArticleSnowflakeId = new SnowflakeIdFactory(2, 2);

    // 实时快讯-全局ID产生对象
    private static volatile SnowflakeIdFactory realTimeSnowflakeId = new SnowflakeIdFactory(2, 3);

    // 财经日历-全局ID产生对象
    private static volatile SnowflakeIdFactory calendarSnowflakeId = new SnowflakeIdFactory(2, 4);

    // 财经日历-主题
    private static volatile SnowflakeIdFactory calendarThemeSnowflakeId = new SnowflakeIdFactory(2, 5);

    // 资讯-所属分类对应关系
    private static volatile SnowflakeIdFactory newsClassificationRelationSnowflakeId = new SnowflakeIdFactory(2, 6);

    // 资讯-分类定义
    private static volatile SnowflakeIdFactory newsClassificationSnowflakeId = new SnowflakeIdFactory(2, 7);

    // 资讯-版块定义
    private static volatile SnowflakeIdFactory newsSectionSnowflakeId = new SnowflakeIdFactory(2, 8);

    // 资讯-板块与分类对应关系
    private static volatile SnowflakeIdFactory newsSectionRelationSnowflakeId = new SnowflakeIdFactory(2, 9);

    // 资讯-审核记录
    private static volatile SnowflakeIdFactory newsCheckRecordSnowflakeId = new SnowflakeIdFactory(2, 10);

    // 资讯-文件生成名称
    private static volatile SnowflakeIdFactory newsFileNameSnowflakeId = new SnowflakeIdFactory(2, 11);

    // 资讯-文件唯一标识
    private static volatile SnowflakeIdFactory newsFileOnlyIdentitySnowflakeId = new SnowflakeIdFactory(2, 12);

    // 资讯-资源与文件的关联关系ID
    private static volatile SnowflakeIdFactory newsFileRelationSnowflakeId = new SnowflakeIdFactory(2, 13);

    // 资讯-财经日历假日信息ID
    private static volatile SnowflakeIdFactory newsCalendarHolidaySnowflakeId = new SnowflakeIdFactory(2, 14);

    // 资讯-财经日历事件信息ID
    private static volatile SnowflakeIdFactory newsCalendarEventSnowflakeId = new SnowflakeIdFactory(2, 15);

    // 资讯-交易所信息ID
    private static volatile SnowflakeIdFactory newsExchangeSnowflakeId = new SnowflakeIdFactory(2, 16);

    // 用户授权信息ID
    private static volatile SnowflakeIdFactory userAuthInfoSnowflakeId = new SnowflakeIdFactory(4, 1);

    // 用户授权日志信息ID
    private static volatile SnowflakeIdFactory userAuthLogInfoSnowflakeId = new SnowflakeIdFactory(4, 2);

    // 用户授权缓存信息ID
    private static volatile SnowflakeIdFactory userCacheAuthInfoSnowflakeId = new SnowflakeIdFactory(4, 3);

    // 个人中心用户ID
    private static volatile SnowflakeIdFactory userInfoSnowflakeId = new SnowflakeIdFactory(4, 4);

    // 内容id
    private static volatile SnowflakeIdFactory contentSnowflakeId = new SnowflakeIdFactory(5, 1);

    // 个人中心用户ID
    private static volatile SnowflakeIdFactory groupInfoSnowflakeId = new SnowflakeIdFactory(4, 5);

    // 消息id
    private static volatile SnowflakeIdFactory messageSnowflakeId = new SnowflakeIdFactory(4, 6);

    // 订单id
    private static volatile SnowflakeIdFactory orderSnowflakeId = new SnowflakeIdFactory(5, 2);

    //traceId
    private static volatile SnowflakeIdFactory dubboTraceId = new SnowflakeIdFactory(5,3);

    private static volatile SnowflakeIdFactory chatRoomSnowflakeId = new SnowflakeIdFactory(6, 2);

    @Override
    public long defaultNextId() {
        return defaultSnowflakeId.nextId();
    }

    @Override
    public long ArticleNextId() {
        return ArticleSnowflakeId.nextId();
    }

    @Override
    public long realTimeNextId() {
        return realTimeSnowflakeId.nextId();
    }

    @Override
    public long calendarNextId() {
        return calendarSnowflakeId.nextId();
    }

    @Override
    public long newsClassificationRelationNextId() {
        return newsClassificationRelationSnowflakeId.nextId();
    }

    @Override
    public long newsClassificationNextId() {
        return newsClassificationSnowflakeId.nextId();
    }

    @Override
    public long newsSectionNextId() {
        return newsSectionSnowflakeId.nextId();
    }

    @Override
    public long newsSectionRelationNextId() {
        return newsSectionRelationSnowflakeId.nextId();
    }

    @Override
    public long newsCheckRecordNextId() {
        return newsCheckRecordSnowflakeId.nextId();
    }

    @Override
    public long newsFileNameNextId() {
        return newsFileNameSnowflakeId.nextId();
    }

    @Override
    public long newsFileOnlyIdentityNextId() {
        return newsFileOnlyIdentitySnowflakeId.nextId();
    }

    @Override
    public long newsFileRelationNextId() {
        return newsFileRelationSnowflakeId.nextId();
    }

    @Override
    public long newsCalendarThemeNextId() {
        return calendarThemeSnowflakeId.nextId();
    }

    @Override
    public long newsCalendarHolidayNextId() {
        return newsCalendarHolidaySnowflakeId.nextId();
    }

    @Override
    public long newsCalendarEventNextId() {
        return newsCalendarEventSnowflakeId.nextId();
    }

    @Override
    public long newsExchangeNextId() {
        return newsExchangeSnowflakeId.nextId();
    }

    @Override
    public long userAuthInfoNextId() {
        return userAuthInfoSnowflakeId.nextId();
    }

    @Override
    public long userAuthLogNextId() {
        return userAuthLogInfoSnowflakeId.nextId();
    }

    @Override
    public long userCacheAuthInfoNextId() {
        return userCacheAuthInfoSnowflakeId.nextId();
    }

    @Override
    public long userInfoNextId() {
        return userInfoSnowflakeId.nextId();
    }

    @Override
    public long groupInfoNextId() {
        return groupInfoSnowflakeId.nextId();
    }

    @Override
    public long contentNextId() {
        return contentSnowflakeId.nextId();
    }

    @Override
    public long messageNextId() {
        return messageSnowflakeId.nextId();
    }

    @Override
    public long orderNextId() {
        return orderSnowflakeId.nextId();
    }

    @Override
    public long chatRoomNextId() {
        return chatRoomSnowflakeId.nextId();
    }

    @Override
    public long dubboTraceId() {
        return dubboTraceId.nextId();
    }

}
