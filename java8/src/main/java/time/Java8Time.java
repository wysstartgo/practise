package time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * 【标题】:伴随lambda表达式、streams以及一系列小优化，Java 8 推出了全新的日期时间API，
 * 在教程中我们将通过一些简单的实例来学习如何使用新API。Java处理日期、日历和时间的方式一直为社区所诟病，
 * 将 java.util.Date设定为可变类型，以及SimpleDateFormat的非线程安全使其应用非常受限。Java也意识到需要一个更好的
 * API来满足社区中已经习惯了使用JodaTime API的人们。全新API的众多好处之一就是，明确了日期时间概念，例如：瞬时（instant）、
 * 长短（duration）、日期、时间、时区和周期。同时继承了Joda库按人类语言和计算机各自解析的时间处理方式。不同于老版本，新API基于ISO标准日历系统，
 * java.time包下的所有类都是不可变类型而且线程安全。下面是新版API中java.time包里的一些关键类：

 Instant：瞬时实例。
 LocalDate：本地日期，不包含具体时间 例如：2014-01-14 可以用来记录生日、纪念日、加盟日等。
 LocalTime：本地时间，不包含日期。
 LocalDateTime：组合了日期和时间，但不包含时差和时区信息。
 ZonedDateTime：最完整的日期时间，包含时区和相对UTC或格林威治的时差。
 新API还引入了ZoneOffSet和ZoneId类，使得解决时区问题更为简便。解析、格式化时间的DateTimeFormatter类也全部重新设 计。
 注意，这篇文章是我在一年前Java 8即将发布时写的，以下示例代码中的时间都是那一年的，当运行这些例子时会返回你当前的时间。
 * 【描述】: http://www.importnew.com/15637.html
 * https://www.liaoxuefeng.com/article/00141939241051502ada88137694b62bfe844cd79e12c32000
 *
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2018-01-23 15:41
 * </pre>
 */
public class Java8Time {

    public static void main(String[] args) {

        // Instant now = Instant.now();
        // Instant start = now.minus(Duration.ofDays(1))
        //// .with(ChronoField.HOUR_OF_DAY,0)
        //// .with(ChronoField.MINUTE_OF_HOUR,0)
        //// .with(ChronoField.SECOND_OF_MINUTE,0)
        //// .with(ChronoField.MILLI_OF_SECOND,0);
        // System.out.println(start);
        // System.out.println(now);
        // // Duration.between()
        LocalDate now = LocalDate.now();
        LocalDate yester = now.minusDays(1);
        System.out.println(now);
        System.out.println(yester);

        LocalDate yestDate = now.minusDays(1);
        LocalDate yestYestDate = yestDate.minusDays(1);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = dateTimeFormatter.format(yestDate);
        System.out.println(format);

        LocalDateTime localDateTime = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);;
        LocalDateTime localDateTime1 = localDateTime.minusDays(1);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTimeFormatter2.format(localDateTime));
        System.out.println(dateTimeFormatter2.format(localDateTime1));

        String startTime = "2017-01-22";
        String endTime = "2018-01-22";
        LocalDateTime localDateTime2 = LocalDateTime.of(LocalDate.parse(startTime), LocalTime.MIDNIGHT);
        System.out.println(dateTimeFormatter2.format(localDateTime2));

        Clock clock = Clock.systemUTC();
        System.out.println("Clock : " + clock);
        System.out.println(clock.millis());

        // Returns time based on system clock zone
        Clock defaultClock = Clock.systemDefaultZone();
        System.out.println("Clock : " + clock);

        LocalDate now1 = LocalDate.now();
        System.out.println(now);
        int dayOfMonth = now.getDayOfMonth();
        System.out.println(dayOfMonth);
//              
//        Month month = now.getMonth();
//        int days = month.get(ChronoField.DAY_OF_MONTH);
//        System.out.println(days);
        //return TimeUnit.DAYS.toMillis(days);

    }
}
