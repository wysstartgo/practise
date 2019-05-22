package com.learn

import java.io.{BufferedWriter, File, FileOutputStream, OutputStreamWriter}
import java.net.URLEncoder
import java.nio.file.{Files, Paths}
import java.util.function.Consumer
import java.util.regex.Pattern

import com.learn.SparkTfIdfTestTokerlizer.clearComment
import org.ansj.recognition.impl.StopRecognition
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.commons.io.IOUtils
import org.apache.commons.lang.{StringEscapeUtils, StringUtils}
import org.apache.spark.ml.feature.{HashingTF, IDF}
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.sql.{Encoders, SparkSession}
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.nlpcn.commons.lang.tire.library.Library

import scala.io.Source

/**
  * <pre>
  *
  * @title: SparkTestRdd
  * @description:
  * @company: 润投科技
  * @author: wuys
  * @datetime: 2019-04-22 14:04
  *            </pre>
  */
object SparkTestRdd {
  /**
    * 去掉注释和平特殊字符
    */
  val regex:String = "\\<!--(.+)--\\>|\\\\n|[`~!@#$%^&*()+=|{}':'\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]" ;


  def clearComment(html:String):String = {
    val p = Pattern.compile(regex)
    var newHtml = Jsoup.clean(html, Whitelist.none()) //jsoup得到的html代码
    val m = p.matcher(newHtml)
    while ( {
      m.find
    }) newHtml = newHtml.replace(m.group(),"")
    newHtml
  }

  def splitStringContent(): Unit = {
    val spark = SparkSession.builder().master("local").appName("test").getOrCreate()
//    val df = spark.createDataFrame(Seq((StringEscapeUtils.unescapeHtml("&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725779415695360.png\\\" title=\\\"继续突破or M型顶？比特币短线尚未走出迷雾但已进入上升抛物线趋势\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;本文来自小葱区块链，阅读更多请登陆&lt;a&gt;www.xcong.com&lt;/a&gt;或小葱APP&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;转载请注明出处&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;大家壕，本栏目为小葱APP原创栏目——小葱龙虎榜，持续追踪每日资金流入/流出最多的各十大币种。本栏目由小葱APP和AICoin联合推出。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;==本文数据来源：AICoin==&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;本文数据皆以人民币进行统计&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;--------------------------------&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;今天数字货币市场大部分反弹，比特币重新回到5100美元以上，而且一度突破5200美元，现在稍稍回落，其它主流币种大多数跟随上涨。而昨天受益BSV被交易所下架消息的BCH今天小幅下跌，BSV继续下跌。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;昨天火币PRIME第二期NEW开盘，盘前HT曾一度上涨，但开盘后HT回落，今天小幅下跌。另外的平台币BNB和OKB均上涨，OKB领涨。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;市值方面，数字货币市场总市值为1781.31亿美元，24小时成交量为397.58亿美元。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流入前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;比特币的资金净流出情况有所扭转，今天转为流入16.75亿，基本上获得流入的币种今天都上涨了，受比特币影响比较大。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725781420572672.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流出前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;BCH和BSV今天都遭到资金净流出，前者流出1.26亿，后者流出2419万。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725784344002560.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;h2&gt;1、比特币&lt;/h2&gt;\\n&lt;br&gt;\\n&lt;p&gt;比特币昨天下探到4935美元就止步了，没有继续下行击穿上周五的低点支撑位，然后开始反弹，今天一度反弹回到5200美元，现在小幅回落，但也仍有继续拉升的可能。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;现在的一个问题是，本次反弹能否反弹突破下面红框处的上沿压力位，即5407美元的高点，如果能突破，则进入新的箱体空间，如果不能突破，要么形成一个短期的M型双顶，要么根本就无法反弹摸到5407这个点位，波峰的动能减弱，这两种情况都是反弹不力，需要谨慎。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;行情明确是等到比特币价格真正突破5407这个压力位之后。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725786269188096.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;从长期走势来看，有市场分析师的观点是非常乐观的。Twitter上颇受欢迎的加密货币分析师Lisa N. Edwards在最近的一条推文中谈到了比特币的价格走势，她解释说，比特币已经进入抛物线型走势，没有太多进一步下跌的空间。如果这种模式在未来几个月持续下去，比特币的价格将很容易飙升至7000美元水平。同时，5000美元附近水平仍将发挥支撑位作用。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725788529917952.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;短线资金方面，由于反弹，资金又处于流入状态，过去24小时流入16.75亿，美元重新开始净流入，而且金额达到11.39亿人民币，另一大主力USDT交易对也是流入状态，韩元也是流入比较大的资金。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725790799036416.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;近三天内涨幅超过20%的今天没有数据，连阳币仅有一只，DGB。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725793936375808.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;截止昨天，DGB是三连阳，其实今天继续上涨，目前是四连阳。从2月份开始，DGB的走势出现大幅的盘中波动，留下平整的上影线和下影线，有人为操纵的迹象。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;价格重心陆续抬升，今天上涨超过了之前的平台压力位，可以继续看价格是否能真正上涨突破资金控盘的高点位置。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;DGB目前消息面并无特别利好。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725794880094208.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt; &lt;/p&gt;")
//      ,1))).toDF("sentence","label")
    val str1 = "&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725779415695360.png\\\" title=\\\"继续突破or M型顶？比特币短线尚未走出迷雾但已进入上升抛物线趋势\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;本文来自小葱区块链，阅读更多请登陆&lt;a&gt;www.xcong.com&lt;/a&gt;或小葱APP&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;转载请注明出处&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;大家壕，本栏目为小葱APP原创栏目——小葱龙虎榜，持续追踪每日资金流入/流出最多的各十大币种。本栏目由小葱APP和AICoin联合推出。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;==本文数据来源：AICoin==&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;本文数据皆以人民币进行统计&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;--------------------------------&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;今天数字货币市场大部分反弹，比特币重新回到5100美元以上，而且一度突破5200美元，现在稍稍回落，其它主流币种大多数跟随上涨。而昨天受益BSV被交易所下架消息的BCH今天小幅下跌，BSV继续下跌。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;昨天火币PRIME第二期NEW开盘，盘前HT曾一度上涨，但开盘后HT回落，今天小幅下跌。另外的平台币BNB和OKB均上涨，OKB领涨。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;市值方面，数字货币市场总市值为1781.31亿美元，24小时成交量为397.58亿美元。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流入前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;比特币的资金净流出情况有所扭转，今天转为流入16.75亿，基本上获得流入的币种今天都上涨了，受比特币影响比较大。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725781420572672.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流出前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;BCH和BSV今天都遭到资金净流出，前者流出1.26亿，后者流出2419万。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725784344002560.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;h2&gt;1、比特币&lt;/h2&gt;\\n&lt;br&gt;\\n&lt;p&gt;比特币昨天下探到4935美元就止步了，没有继续下行击穿上周五的低点支撑位，然后开始反弹，今天一度反弹回到5200美元，现在小幅回落，但也仍有继续拉升的可能。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;现在的一个问题是，本次反弹能否反弹突破下面红框处的上沿压力位，即5407美元的高点，如果能突破，则进入新的箱体空间，如果不能突破，要么形成一个短期的M型双顶，要么根本就无法反弹摸到5407这个点位，波峰的动能减弱，这两种情况都是反弹不力，需要谨慎。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;行情明确是等到比特币价格真正突破5407这个压力位之后。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725786269188096.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;从长期走势来看，有市场分析师的观点是非常乐观的。Twitter上颇受欢迎的加密货币分析师Lisa N. Edwards在最近的一条推文中谈到了比特币的价格走势，她解释说，比特币已经进入抛物线型走势，没有太多进一步下跌的空间。如果这种模式在未来几个月持续下去，比特币的价格将很容易飙升至7000美元水平。同时，5000美元附近水平仍将发挥支撑位作用。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725788529917952.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;短线资金方面，由于反弹，资金又处于流入状态，过去24小时流入16.75亿，美元重新开始净流入，而且金额达到11.39亿人民币，另一大主力USDT交易对也是流入状态，韩元也是流入比较大的资金。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725790799036416.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;近三天内涨幅超过20%的今天没有数据，连阳币仅有一只，DGB。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725793936375808.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;截止昨天，DGB是三连阳，其实今天继续上涨，目前是四连阳。从2月份开始，DGB的走势出现大幅的盘中波动，留下平整的上影线和下影线，有人为操纵的迹象。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;价格重心陆续抬升，今天上涨超过了之前的平台压力位，可以继续看价格是否能真正上涨突破资金控盘的高点位置。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;DGB目前消息面并无特别利好。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://xxx.com/collect/article/236725794880094208.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt; &lt;/p&gt;"
    val str2 = "&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;img src=\"https://xxx.com/collect/article/236725779415695360.png\"&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;&lt;br&gt;&lt;/p&gt;&lt;p&gt;2019年一季度，正邦科技实现营收51.94亿元，较上年同期增长4.74%，归属于上市公司股东净利润为-4.14亿元，较上年同期大幅下滑743.42%，期内经营活动产生的现金流量净额为-1.972亿元，较上年同期增长60.67%。&lt;/p&gt;&lt;p&gt;2018年全年净利润下滑超过60%，今年一季度巨亏4亿元，在这样的业绩背景下，周一（4月21日）开盘，正邦科技股价开盘却直奔涨停，盘中一度上涨至9.68%，截至午盘，正邦科技报20.36元/股，上涨7.16%。而在4月1日，正邦科技曾发布2019年业绩预亏公告，但公告发布当日股价涨停。&lt;/p&gt;&lt;p&gt;4月1日，深交所对其发布关注函，公司2019年一季度预计亏损3.8亿元-4.3亿元，但公司自2019年1月1日至3月29日起，公司股价累计涨幅201.67%，深交所对此表示高度关注。并要求公司说明2019年一季度大额亏损的原因及合理性，并对公司股价波动情况及业绩情况作出充分的风险提示。&lt;/p&gt;&lt;p&gt;对于一季度巨亏，正邦科技表示，2019年1-2月份，国内生猪价格持续走低，进入3月份猪价有所回升，2019年一季度，公司预计销售生猪170万头左右；其中，商品猪销量预计150万头左右，销售均价11元/公斤左右，是造成公司一季度大额亏损的主要原因，2018年一季度，商品猪价格在12.58元/公斤左右；仔猪销量预计20万头左右，对利润影响不大。&lt;/p&gt;&lt;p&gt;其次，受非洲猪瘟影响，正邦科技加大了生物安全防控方面的投入，相关成本成本支出在1亿元以上，这使公司2019年一季度成本上升较快。生猪销售价格较低，叠加成本上升，成为正邦科技一季度巨亏的主要原因。&lt;/p&gt;&lt;p&gt;业绩巨亏，股价走高，在此轮生猪行情中得到展示。除正邦科技外，牧原股份2019年一季度预亏5.2亿-5.6亿元，天邦股份预计亏损3.0亿-3.6亿元，但近一月来，牧原股份上涨了51.39%，近三月上涨了142.23%，天邦股份近一月上涨了71.38%，近三个月上涨180%。&lt;/p&gt;&lt;p&gt;正邦科技在回复关注函的公告中表示，根据同花顺数据显示，截至2019年4月1日，公司市值在近1个月和近3个月内，涨幅分别达到52.46%和238.10%，与同类上市公司相比，涨幅较大，且近三年，尽管公司营业总收入不断增长，但归属于母公司所有者的净利润呈持续下滑趋势，敬请投资者注意投资风险。&lt;/p&gt;&lt;p&gt;但这份公告并未止住正邦科技股价持续走高的情况，从4月1日-4月22日午盘，正邦科技股价累计上涨超过20%，猪肉板块股价累计上涨超过10%。&lt;/p&gt;&lt;p&gt;天风证券研究报告表示：低猪价是构成正邦科技一季度亏损的核心原因，三月份来看，全国猪价已经开始快速上涨，3月份的出栏均价达到14.54元/公斤，随着猪价的上涨，公司业绩拐点也逐步到来。&lt;/p&gt;&lt;p&gt;天风证券研报还表示：正邦科技市值与同行相比公司头均市值较低，对应2020年的出栏量和2019年3月29日的收盘市值，温氏股份、牧原股份和天邦股份的头均市值分别为5918元、8250元和3460元，正邦科技的头均市值仅3191元，在主要的生猪养殖企业中估值较低。这些或许是正邦科技股价继续上涨的原因。&lt;/p&gt;&lt;p&gt;股价不断上涨的同时，正邦科技高管却在减持公司股票，如董事长程凡贵、董事会秘书王飞、财务总监周锦明，董事刘道君均在这一期间计划或已不同程度减持了公司股票。&lt;/p&gt;&lt;p&gt;截至2019年一季度，公司大股东正邦集团有限公司持有公司19.77%的股票，持股数量4.69亿股，质押2700万股，公司第二大股东江西永联农业控股有限公司持股比例同为19.77%，持股数为4.6887亿股，质押3.85935亿股，质押比例达82.31%。&lt;/p&gt;"
    val str3 = "试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付费测试内容付"
    val str4 = "行情明天会涨btc明天会涨"
    val str5 = "testtesttest内容付费"
    val strArr1: String = tokenAnalysis(spark, str1)
    val strArr2: String = tokenAnalysis(spark,str2)
    val strArr3: String = tokenAnalysis(spark,str3)
    val strArr4: String = tokenAnalysis(spark,str4)
    val strArr5: String = tokenAnalysis(spark,str5)
    val wordsData = spark.createDataFrame(Seq((123L,strArr1.split(" ")),
      (456L,strArr2.split(" ")),
      (780L,strArr3.split(" ")),
      (123L,strArr4.split(" ")),
      (456L,strArr5.split(" "))
    )).toDF("label","words")
    val hashingTF = new HashingTF().setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(100)
    //class[label[0]: bigint, words[0]: array<string>, rawFeatures[0]: vector]
    val featurizedData = hashingTF.transform(wordsData)
    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    val idfModel = idf.fit(featurizedData)
    val wordMap = wordsData.select("words").rdd.flatMap{
      row => {
        row.getAs[Seq[String]](0).map{
          w => (hashingTF.indexOf(w),w)
        }
      }
    }.collect().toMap
    //class[label[0]: bigint, words[0]: array<string>, rawFeatures[0]: vector, features[0]: vector]
    val rescaledData = idfModel.transform(featurizedData)
    rescaledData.take(10).foreach(println)
    println("========================================================")
    rescaledData.select("features").foreach(row => println(row.toString()))
    println("--------------------------------------------------------")
    val keyWords = rescaledData.select("features").rdd.map{
      x => {
        //x的值:
        //[(100,[22,24,25,26,28,31,34,38,39,40,44,48,52,55,59,60,63,68,72,80,84,87,95,96],[1.0986122886681098,3.295836866004329,
        // 4.394449154672439,0.4054651081081644,1.0986122886681098,1.0986122886681098,3.295836866004329,2.1972245773362196,
        // 1.3862943611198906,1.0986122886681098,1.0986122886681098,2.1972245773362196,8.788898309344878,1.3862943611198906,
        // 1.0986122886681098,3.295836866004329,1.0986122886681098,1.0986122886681098,1.6218604324326575,3.295836866004329,
        // 1.0986122886681098,1.0986122886681098,1.0986122886681098,1.0986122886681098])]

        //v的值为:
        //(100,[22,24,25,26,28,31,34,38,39,40,44,48,52,55,59,60,63,68,72,80,84,87,95,96],
        // [1.0986122886681098,3.295836866004329,4.394449154672439,0.4054651081081644,1.0986122886681098,
        // 1.0986122886681098,3.295836866004329,2.1972245773362196,1.3862943611198906,1.0986122886681098,
        // 1.0986122886681098,2.1972245773362196,8.788898309344878,1.3862943611198906,1.0986122886681098,
        // 3.295836866004329,1.0986122886681098,1.0986122886681098,1.6218604324326575,3.295836866004329,
        // 1.0986122886681098,1.0986122886681098,1.0986122886681098,1.0986122886681098])
        val v = x.getAs[SparseVector](0)
        //v.indices.foreach(println)
        //v.indices.zip(v.values)的值为：
        //(22,1.0986122886681098) (24,3.295836866004329) (25,4.394449154672439)(26,0.4054651081081644)(28,1.0986122886681098)
        //(31,1.0986122886681098)(34,3.295836866004329)(38,2.1972245773362196)(39,1.3862943611198906)(40,1.0986122886681098)
        //(44,1.0986122886681098)(48,2.1972245773362196)(52,8.788898309344878)(55,1.3862943611198906)(59,1.0986122886681098)
        //(60,3.295836866004329)(63,1.0986122886681098)(68,1.0986122886681098)(72,1.6218604324326575)(80,3.295836866004329)
        //(84,1.0986122886681098)(87,1.0986122886681098)(95,1.0986122886681098)(96,1.0986122886681098)
        v.indices.zip(v.values).foreach(x => {
          println("x:" + x._1 + "  " + x._2)
        })
        //对v.indices.zip(v.values)的结果按第二个值从大到小排序
        v.indices.zip(v.values).sortWith((a,b) => {
          println("a:" + a._1 + "  " + a._2 + "  b:" + b._1 + "   " + b._2)
          a._2 > b._2
        }).take(10).map(x => (wordMap.get(x._1).get,x._2))
      }
    }

//    keyWords.foreach(arr => {
//      println(arr(0)._1)
//      println(arr(0)._2)
//    })
//    keyWords.foreach(arr => {
//      arr.foreach(inn => {
//        println(inn._1 + " gg " + inn._2)
//      })
//    })
    keyWords.foreach(x => {
      println(x.toSeq)
    })

    println("*********************************************")



  }

  private def tokenAnalysis(spark: SparkSession, str2: String) = {
    val html = StringEscapeUtils.unescapeHtml(str2)
    val finalHtml = clearComment(html)
    println(finalHtml)
    implicit val mapenc = Encoders.kryo[String]
    val stopWords = spark.read.textFile("F:\\workspace\\practise\\SparkStart\\library\\stopword")
      .map(row => StringUtils.trim(row)).collectAsList()
    //词典操作 https://blog.csdn.net/a822631129/article/details/52331202
    //val forest0 = Library.makeForest("E:/base.dic")
    //Library.insertWord()
    println("=================================================")
    val filter = new StopRecognition().insertStopWords(stopWords)
    //filter.insertStopNatures("t","v","a","b","r","m","q","d","p","c","u","e","y","o","h","k","x","w","vshi","vyou")
    //filter.insertStopNatures("t","tg","v","vn","vd","a","b","r","m","q","mq","f","d","p","s","c","u","e","y","o","h","k","x","w")
    //"a","ad","ag","an","b","bg","c","d","dg","e","f","h","i","j","k","l","m","mg","n","ng","nr","ns","nt","nx","nz","o","p","q","r","rg","s","t","tg",
    // "u","ud","ug","uj","ul","uv","uz","v","vd","vg","vn","w","y","yg","z"
    filter.insertStopNatures("a", "ad", "ag", "an", "b", "bg", "c", "d", "dg", "e", "f", "h", "i", "j", "k", "l", "m", "mg", "mq", "n", "ng",
      "nr", "ns", "nt", "nx", "nz", "o", "p", "q", "r", "rg", "s", "t", "tg", "u", "ud", "ug", "uj", "ul", "uv", "uz", "v", "vd", "vg", "vn", "w", "y", "yg", "z")
    filter.insertStopNatures("w", null)
    //自定义词典 参数依次为:自定义词,词性,词频(值越大，重要度越高)
    val forest0 = Library.makeForest("F:\\workspace\\practise\\SparkStart\\library\\dic4")
    //val strArr = ToAnalysis.parse(clearComment(finalHtml),forest0).recognition(filter).toStringWithOutNature(" ")
    val strArr = ToAnalysis.parse(clearComment(finalHtml), forest0).recognition(filter).toStringWithOutNature(" ")
    strArr
  }

  def main(args: Array[String]): Unit = {
    //formatNature
    //refactorFile
    splitStringContent()
  }

  private def formatNature = {
    val filePath = "F:\\workspace\\practise\\SparkStart\\library\\nature"
    val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath + "2"))))
    val source = Source.fromFile(filePath, "utf8")
    val lines = source.getLines
    val sb = new StringBuilder
    for (line <- lines) {
      //val strArr = line.split("        ")
      val strArr = line.split("\t")
      sb.append("\"").append(strArr(2)).append("\"").append(",")
      //val bytes = newLine.getBytes
      bw.write(strArr(2))
      bw.newLine()

    }
    println(sb)
    IOUtils.closeQuietly(bw)
  }



  /**
    * 格式化文件内容
    */
  private def refactorFile = {
    //scala 操作文件     https://www.jianshu.com/p/89be118d2f88
    //    Files.readAllLines(Paths.get("F:\\workspace\\practise\\SparkStart\\library\\dic")).forEach(new Consumer[String] {
    //      override def accept(t: String): Unit = println(t)
    //    })
    val filePath = "F:\\\\workspace\\\\practise\\\\SparkStart\\\\library\\\\dic"
    val bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath + "4"))))
    val source = Source.fromFile(filePath + "3", "utf8")
    val lines = source.getLines
    for (line <- lines) {
      //val strArr = line.split("        ")
      val newLine = line.trim + "\t" + "nl" + "\t" + "100"
      //val bytes = newLine.getBytes
      bw.write(newLine)
      bw.newLine()
    }
    IOUtils.closeQuietly(bw)
  }
}
