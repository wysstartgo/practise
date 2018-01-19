package snowflake;

/**
 * <pre>
 *  
 *
 * 【标题】: 使用Twitter_Snowflake产生全局ID
 * 【描述】: 
 * 【版权】: 润投科技
 * 【作者】: 唐宋  
 * 【时间】: 2017年7月3日 上午10:00:21
 * </pre>
 */
public interface ISnowflakeIdService {

    /**
     * 下一个全局ID-默认通用
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long defaultNextId();

    /**
     * 下一个全局ID-普通资讯 
     * 1:文章类型资讯
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long ArticleNextId();

    /**
     * 下一个全局ID-实时快讯<br/>
     * 
     * @Description 快讯类型资讯
     * @author 唐宋
     * @return
     */
    long realTimeNextId();

    /**
     * 下一个全局ID-财经日历<br/>
     * 
     * @Description -财经日历/财经大事件
     * @author 唐宋
     * @return
     */
    long calendarNextId();

    /**
     * 下一个全局ID-资源与文件的关联关系ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsCalendarThemeNextId();

    /**
     * 下一个全局ID-资讯所属分类对应关系<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsClassificationRelationNextId();

    /**
     * 下一个全局ID-分类定义<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsClassificationNextId();

    /**
     * 下一个全局ID-版块定义<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsSectionNextId();

    /**
     * 下一个全局ID-板块与分类对应关系<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsSectionRelationNextId();

    /**
     * 下一个全局ID-审核记录<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsCheckRecordNextId();

    /**
     * 下一个全局ID-文件生成名称<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsFileNameNextId();

    /**
     * 下一个全局ID-文件唯一标识<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsFileOnlyIdentityNextId();

    /**
     * 下一个全局ID-资源与文件的关联关系ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsFileRelationNextId();

    /**
     * 下一个全局ID-财经日历假日信息ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsCalendarHolidayNextId();

    /**
     * 下一个全局ID-财经日历事件信息ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsCalendarEventNextId();

    /**
     * 下一个全局ID-交易所信息ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long newsExchangeNextId();

    /**
     * 下一个全局ID-用户授权相关信息ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long userAuthInfoNextId();

    /**
     * 下一个全局ID-用户授权日志ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long userAuthLogNextId();

    /**
     * 下一个全局ID-缓存用户授权信息ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long userCacheAuthInfoNextId();
    
    /**
     * 下一个全局ID-个人中心用户ID<br/>
     * 
     * @Description
     * @author 唐宋
     * @return
     */
    long userInfoNextId();

    /**
     * 下一个全局ID-圈子ID<br/>
     * @Description 
     * @author 张国栋
     * @return
     */
    long groupInfoNextId();
    /**
     * 内容
     *
     * @return
     */
    long contentNextId();

    /**
     * 下一个全局ID-消息id<br/>
     * @Description 
     * @author 张国栋
     * @return
     */
    long messageNextId();

    /**
     * 下一个订单ID-消息id<br/>
     * @Description
     * @author 王鸿芳
     * @return
     */
    long orderNextId();


    /**
     * 下一个聊天室id
     * @return
     */
    long chatRoomNextId();

    /**
     * 获取traceId
     * @return
     */
    long dubboTraceId();

}
