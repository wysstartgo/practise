package com.learn

import java.net.URLEncoder
import java.util.regex.Pattern

import com.mongodb.spark.MongoSpark
import org.ansj.recognition.impl.StopRecognition
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.spark.ml.feature.{HashingTF, IDF}
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.sql.SparkSession
import org.jsoup.Jsoup
import org.jsoup.safety.Whitelist
import org.nlpcn.commons.lang.tire.library.Library


/**
  * <pre>
  *
  * @title: SparkTfIdfTest
  * @description:
  * @company: 润投科技
  * @author: wuys
  * @datetime: 2019-04-08 17:36
  *            </pre>
  */
object SparkTfIdfTestTokerlizer {

  val regex:String = "\\<!--(.+)--\\>" ;


  def clearComment(html:String):String = {
    val p = Pattern.compile(regex)
    println("=====*======" + URLEncoder.encode(html,"utf8"))
    var newHtml = Jsoup.clean(html, Whitelist.none()) //jsoup得到的html代码
    val m = p.matcher(newHtml)
    while ( {
      m.find
    }) newHtml = newHtml.replace(m.group(),"")
    println("*****=====" + newHtml)
    newHtml
  }


  def main(args: Array[String]): Unit = {
    //创建spark
    val spark = SparkSession
      .builder()
      .master("local") // 本地测试，否则报错 A master URL must be set in your configuration at org.apache.spark.SparkContext.
      .appName("test")
      // .enableHiveSupport()
      .getOrCreate() // 有就获取无则创建

    val df = spark.createDataFrame(Seq(
//          (0, "Hi I heard about Spark"),
//          (0, "I wish Java could use case classes"),
          ("&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725779415695360.png\\\" " +
            "title=\\\"继续突破or M型顶？比特币短线尚未走出迷雾但已进入上升抛物线趋势\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;" +
            "本文来自小葱区块链，阅读更多请登陆&lt;a&gt;www.xcong.com&lt;/a&gt;或小葱APP&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;" +
            "转载请注明出处&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;大家壕，本栏目为小葱APP原创栏目——小葱龙虎榜，持续追踪每日资金流入/流出最多的各十大币种。" +
            "本栏目由小葱APP和AICoin联合推出。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;==本文数据来源：AICoin==&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;" +
            "本文数据皆以人民币进行统计&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;--------------------------------&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;" +
            "今天数字货币市场大部分反弹，比特币重新回到5100美元以上，而且一度突破5200美元，现在稍稍回落，其它主流币种大多数跟随上涨。" +
            "而昨天受益BSV被交易所下架消息的BCH今天小幅下跌，BSV继续下跌。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;昨天火币PRIME第二期NEW开盘，盘前HT曾一度上涨，" +
            "但开盘后HT回落，今天小幅下跌。另外的平台币BNB和OKB均上涨，OKB领涨。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;市值方面，数字货币市场总市值为1781.31亿美元，" +
            "24小时成交量为397.58亿美元。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流入前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;" +
            "比特币的资金净流出情况有所扭转，今天转为流入16.75亿，基本上获得流入的币种今天都上涨了，受比特币影响比较大。" +
            "&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725781420572672.png\\\"" +
            "&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;过去24小时资金净流出前十的币种&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;BCH和BSV今天都遭到资金净流出，前者流出1.26亿，后者流出2419万。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725784344002560.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;（数据来源：AICoin）&lt;/p&gt;\\n&lt;br&gt;\\n&lt;h2&gt;1、比特币&lt;/h2&gt;\\n&lt;br&gt;\\n&lt;p&gt;比特币昨天下探到4935美元就止步了，没有继续下行击穿上周五的低点支撑位，然后开始反弹，今天一度反弹回到5200美元，现在小幅回落，但也仍有继续拉升的可能。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;现在的一个问题是，本次反弹能否反弹突破下面红框处的上沿压力位，即5407美元的高点，如果能突破，则进入新的箱体空间，如果不能突破，要么形成一个短期的M型双顶，要么根本就无法反弹摸到5407这个点位，波峰的动能减弱，这两种情况都是反弹不力，需要谨慎。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;行情明确是等到比特币价格真正突破5407这个压力位之后。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725786269188096.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;从长期走势来看，有市场分析师的观点是非常乐观的。Twitter上颇受欢迎的加密货币分析师Lisa N. Edwards在最近的一条推文中谈到了比特币的价格走势，她解释说，比特币已经进入抛物线型走势，没有太多进一步下跌的空间。如果这种模式在未来几个月持续下去，比特币的价格将很容易飙升至7000美元水平。同时，5000美元附近水平仍将发挥支撑位作用。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725788529917952.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;短线资金方面，由于反弹，资金又处于流入状态，过去24小时流入16.75亿，美元重新开始净流入，而且金额达到11.39亿人民币，另一大主力USDT交易对也是流入状态，韩元也是流入比较大的资金。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725790799036416.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;近三天内涨幅超过20%的今天没有数据，连阳币仅有一只，DGB。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725793936375808.png\\\"&gt;&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;截止昨天，DGB是三连阳，其实今天继续上涨，目前是四连阳。从2月份开始，DGB的走势出现大幅的盘中波动，留下平整的上影线和下影线，有人为操纵的迹象。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;价格重心陆续抬升，今天上涨超过了之前的平台压力位，可以继续看价格是否能真正上涨突破资金控盘的高点位置。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;DGB目前消息面并无特别利好。&lt;/p&gt;\\n&lt;br&gt;\\n&lt;p&gt;&lt;img src=\\\"https://rtmarket.oss-cn-hangzhou.aliyuncs.com/collect/article/236725794880094208.png\\\"&gt;&lt;" +
            "/p&gt;\\n&lt;br&gt;\\n&lt;p&gt; &lt;/p&gt;",1L)
        )).toDF("sentence","label")
    val stopWords = spark.read.textFile("F:\\workspace\\practise\\SparkStart\\src\\main\\resources\\stopwords.txt").collectAsList()
    //词典操作 https://blog.csdn.net/a822631129/article/details/52331202
    //val forest0 = Library.makeForest("E:/base.dic")
    //Library.insertWord()
    val filter = new StopRecognition().insertStopWords(stopWords)

    //    val files = spark.sparkContext
    //      .wholeTextFiles("/opt/data/20news-bydate-train/*")
    //    val sentences = files.map(f=>News(f._1.split("/").slice(4, 6).mkString("."),f._2)).toDF("name","content").as[News]
//    val sentenceData = spark.createDataFrame(Seq(
//      (0, "Hi I heard about Spark"),
//      (0, "I wish Java could use case classes"),
//      (1, "Logistic regression models are neat")
//    )).toDF("label", "sentence")
    //val stopWords = spark.
    //val tokenizer = new Tokenizer().setInputCol("sentence").setOutputCol("words")
    //val wordsData = tokenizer.transform(df)
    //implicit val mapenc = Encoders.kryo[(String,Array[String])]
    df.show()
   // println("***************************************1")
//词性 https://blog.csdn.net/bitcarmanlee/article/details/53607776
   // df.rdd.foreach(println)
   // println("***************************************1")
    val wordsDataRdd = df.rdd.map(row => { //使用中文分词器将内容分词：[(filename1:w1 w3 w3...),(filename2:w1 w2 w3...)]
      val str = ToAnalysis.parse(clearComment(row.getString(0))).recognition(filter).toStringWithOutNature(" ")
      print(row.toString() + "            999999999999999999999999")
      (row.getLong(1), str.split(" "))
    })
    //如果数据集需要在很多地方用到的时候，最好cache一下
//    wordsDataRdd.cache()
    val wordsData = spark.createDataFrame(wordsDataRdd).toDF("label","words")
    wordsData.show()
    val hashingTF = new HashingTF()
      .setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(200)
    val featurizedData = hashingTF.transform(wordsData)
    // CountVectorizer也可获取词频向量

    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    val idfModel = idf.fit(featurizedData)
    val rescaledData = idfModel.transform(featurizedData)
   // rescaledData.select("features", "label").take(3).foreach(println)
    val wordMap = wordsData.select("words").rdd.flatMap{
      row => {
        row.getAs[Seq[String]](0).map{
          w => (hashingTF.indexOf(w),w)
        }
      }
    }.collect().toMap

    val keyWords = rescaledData.select("features").rdd.map{
      x => {
        //idf结果以稀疏矩阵保存
        val v = x.getAs[SparseVector](0)
        v.indices.zip(v.values).sortWith((a,b) => {
          a._2 > b._2
          ////根据idf值从大到小排序，取前10个，并通过索引反查到词
          //[(文章1的关键词索引1:tf-idf值,文章1的关键词索引2:tf-idf值),(文章n的关键词索引1:tf-idf值,文章n的关键词索引2:tf-idf值)...],每组()表示一个新闻的关键词
        }).take(10).map(x => (wordMap.get(x._1).get,x._2))
      }
    }

    keyWords.foreach(x => {
      println(x.toSeq)
    })


//    MongoSpark.load(spark).
//      .withPipeline(Seq(Document.parse("{ $match: { orig :  'KMG'  } }")))
//      .map(doc=>(doc.getString("flight") ,doc.getLong("seats")))
//      .reduceByKey((x,y)=>(x+y))
//      .take(10)
//      .foreach(println)
  }

}
