package com.learn

import java.util.regex.Pattern

import com.mongodb.spark.MongoSpark
import org.ansj.recognition.impl.StopRecognition
import org.ansj.splitWord.analysis.ToAnalysis
import org.apache.spark.ml.feature.{HashingTF, IDF, Tokenizer}
import org.apache.spark.ml.linalg.SparseVector
import org.apache.spark.sql.{Encoders, SparkSession}
import org.apache.spark.sql.functions._
import org.bson.Document
import org.json.JSONObject
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
object SparkTfIdfTest {

  val regex:String = "\\<!--(.+)--\\>" ;


  def clearComment(html:String):String = {
    val p = Pattern.compile(regex)
    var newHtml = Jsoup.clean(html, Whitelist.none()) //jsoup得到的html代码
    val m = p.matcher(newHtml)
    while ( {
      m.find
    }) newHtml = newHtml.replace(m.group(),"")

    newHtml
  }


  def main(args: Array[String]): Unit = {
    //创建spark
    val spark = SparkSession
      .builder()
      .master("local") // 本地测试，否则报错 A master URL must be set in your configuration at org.apache.spark.SparkContext.
      .appName("test")
      .config("spark.mongodb.input.uri","mongodb://zx:zx123456@192.168.1.41:28071/zx.business_collect_article_info")
      .config("spark.mongodb.output.uri","mongodb://zx:zx123456@192.168.1.41:28071/zx.business_collect_article_info")
      // .enableHiveSupport()
      .getOrCreate() // 有就获取无则创建

    var df = MongoSpark.load(spark)
//    val switchF :(String => Int) = (_:String) {0}
//    val addCol = udf(switchF)
    //df = df.filter(df.col("state").equalTo(2)).select("freeContent").withColumn("label",addCol(df("freeContent"))).toDF("sentence","label")
    df = df.filter(df.col("state").equalTo(1)).select("freeContent","newsId").toDF("sentence","label")
    //println(df.rdd.count)
    //println(df.rdd.first.toSeq)
    val stopWords = spark.read.textFile("F:\\workspace\\practise\\SparkStart\\src\\main\\resources\\stopwords.txt").collectAsList()
    //词典操作 https://blog.csdn.net/a822631129/article/details/52331202
    val forest0 = Library.makeForest("E:/base.dic")
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
    println("***************************************1")
//词性 https://blog.csdn.net/bitcarmanlee/article/details/53607776

    val wordsDataRdd = df.rdd.map(row => { //使用中文分词器将内容分词：[(filename1:w1 w3 w3...),(filename2:w1 w2 w3...)]
      val str = ToAnalysis.parse(clearComment(row.getString(0)),forest0).recognition(filter).toStringWithOutNature(" ")
      (row.getLong(1), str.split(" "))
    })
    val wordsData = spark.createDataFrame(wordsDataRdd).toDF("label","words")
    wordsData.show()
    println("================================1")

    val hashingTF = new HashingTF()
      .setInputCol("words").setOutputCol("rawFeatures").setNumFeatures(200)
    val featurizedData = hashingTF.transform(wordsData)
    // CountVectorizer也可获取词频向量

    val idf = new IDF().setInputCol("rawFeatures").setOutputCol("features")
    val idfModel = idf.fit(featurizedData)
    val rescaledData = idfModel.transform(featurizedData)
   //将每个词条在hashingTF中的index与词条建立映射关系
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
