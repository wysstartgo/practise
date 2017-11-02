package bloomfilter;

/**
 * <pre>
 *
 * 【标题】: 北京-菜鸟多年 2016/12/14 17:41:55
             用户id有序？是数字？
             北京-Mr.Win 2016/12/14 17:42:10
             现在设计和你说的一样 @北京-MUDER
             济南-JAVA 2016/12/14 17:42:15
             我们的消息是处理了，才会去不同的表里面。如果只是阅读过是置灰色，然后未处理的置顶
             北京-Mr.Win 2016/12/14 17:42:32
             用redis去标记 该用户是否查询了该消息
             北京-菜鸟多年 2016/12/14 17:42:33
             满足这2个条件  使用bitmap存储是否 给用户插过消息了
             北京-菜鸟多年 2016/12/14 17:42:46
             基本上不占空间
 Bit-Map方法。建立一个BitSet，将每个URL经过一个哈希函数映射到某一位。

 @see http://www.cnblogs.com/heaad/archive/2011/01/02/1924195.html
 @see http://blog.csdn.net/jdsjlzx/article/details/43916241
 * 【描述】:
 * 【版权】: 润投科技
 * 【作者】: wuys
 * 【时间】: 2017-10-24 14:14
 * </pre>
 */
public class BitMapTest {
}
