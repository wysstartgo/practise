package com.learn

import com.mongodb.spark.MongoSpark
import com.mongodb.spark.sql.helpers.StructFields
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{Column, Encoders, SparkSession}
import org.apache.spark.sql.types.DataTypes

import scala.reflect.internal.util.TableDef.Column

import org.apache.spark.sql.functions.row_number
import org.apache.spark.sql.functions.udf
import org.apache.spark.sql.functions.col

/**
  * <pre>
  *
  * @title: SparkMongodbTest
  * @description:
  * @company: 润投科技
  * @author: wuys
  * @datetime: 2019-04-18 16:47
  *            </pre>
  */
object SparkMongodbTest {

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
//    val schema = DataTypes.createStructType(Array(
//      StructFields.objectId("_id", nullable = false),
//      DataTypes.createStructField("freeContent", DataTypes.StringType, false))
//    )
    var df = MongoSpark.load(spark)
    df = df.filter(df.col("state").equalTo(1))

    df.printSchema()

    df = df.select("title")
//    df = df.select("title").toDF("news")
//    println(df.select("news").first.toSeq)
    println("==========================================")
    //@see http://www.cnblogs.com/dongxiucai/p/10002697.html
    //implicit val mapenc = Encoders.kryo[(Int,String)]
    //将一列从一个字符串变成一个元组
    //df = df.map(row => (1,row.getString(0))).toDF("label","title")
    //println(df.map(row => (1,row.getString(0))).first)
//    val w = Window.orderBy("count")
//    df.withColumn("index",row_number().over(w))
    //df.withColumn("label",).show()
    val code :(String => Int) = (_: String) => {1}
    val addCol = udf(code)
    println(addCol)
    df.withColumn("label",addCol(df("title"))).show()
    //println("*****" + df.select("title").first.toSeq)
   // println("*************" + df.first.toSeq)
   // println("==========================================")
   // println(df.first.toSeq)
    //println(df.rdd.pipe("{\"freeContent\":1}").first.toSeq)
  }

}
