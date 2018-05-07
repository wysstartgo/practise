package com.learn

import org.apache.spark.mllib.linalg.{Matrices, Vectors}
import org.apache.spark.mllib.linalg.distributed.RowMatrix
import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 矩阵
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 16:45
  * </pre>
  */
object TestMatrix {

  def main(args: Array[String]): Unit = {
    rowMartix
  }

  def simpleMartix: Unit = {
    val mx = Matrices.dense(2, 3, Array(1, 2, 3, 4, 5, 6))
    println(mx)
  }

  def rowMartix: Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("rowMartix")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("E:\\workspace\\SparkStart\\src\\main\\resources\\matrix.txt").map(_.split(' ').map(_.toDouble)).map(line => Vectors.dense(line))
    val rm = new RowMatrix(rdd)
    println(rm.numRows())
    println(rm.numCols())
  }



}
