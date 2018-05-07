package com.learn

import org.apache.spark.mllib.linalg.distributed.{CoordinateMatrix, MatrixEntry}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 坐标矩阵
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 17:42
  * </pre>
  */
object CoordinateRowMatrix {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("CoordinateRowMatrix")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("c://a.txt").map(_.split(' ').map(_.toDouble)).map(vue => (vue(0).toLong,vue(1).toLong,vue(2)))
        .map(vue2 => new MatrixEntry(vue2._1,vue2._2,vue2._3))
    val crm = new CoordinateMatrix(rdd)
    println(crm.entries.foreach(println))
  }

}
