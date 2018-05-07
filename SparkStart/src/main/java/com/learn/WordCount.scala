package com.learn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: wordCount
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-03 11:43
  * </pre>
  */
object WordCount {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("wordCount")
    val sc = new SparkContext(conf)
    val data = sc.textFile("E:\\workspace\\SparkStart\\src\\main\\resources\\wordcount.txt")
    data.flatMap(_.split(" ")).map((_,1)).reduceByKey(_ + _).collect().foreach(println)
  }

}
