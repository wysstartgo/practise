package com.learn

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.stat.Statistics
import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 17:50
  * </pre>
  */
object TestSummary {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("testSummary")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("c://a.txt").map(_.split(' ').map(_.toDouble)).map(line => Vectors.dense(line))
    val summary = Statistics.colStats(rdd)
    //计算均值
    println(summary.mean)
    //计算标准差
    println(summary.variance)

  }

  def distance: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("TestSummary")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("c://a.txt").map(_.split(' ').map(_.toDouble)).map(line => Vectors.dense(line))
    val summary = Statistics.colStats(rdd)
    //计算曼哈顿距离
    println(summary.normL1)
    //计算欧几里得距离
    println(summary.normL2)
  }

}
