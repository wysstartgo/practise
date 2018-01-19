import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import util.DateFormatUtil;

import java.util.Date;

/**
 * <pre>
 *
 * 【标题】:
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-11-17 10:33
 * </pre>
 */
public class JodeTimeTest {

    public static void main(String[] args) {
        String time = "2017-11-16T07:00:00.000Z";
//        time = time.replace("Z", " UTC");//注意是空格+UTC
//        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
//        DateTime dateTime = DateTime.parse(time, dateTimeFormatter);
//        System.out.println(dateTime);
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZone(DateTimeZone.UTC);
        DateTime dateTime = dateTimeFormatter.parseDateTime(time);
        System.out.println(dateTime);
        Date date = new Date(dateTime.getMillis());
        System.out.println(date);
        System.out.println(DateFormatUtil.formatSdfymdhmsSSS(date));
        System.out.println(DateFormatUtil.formatY_m_dhms(date));
    }
}
