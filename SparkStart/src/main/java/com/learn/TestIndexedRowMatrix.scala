package com.learn

import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.linalg.distributed.{IndexedRow, IndexedRowMatrix}
import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 17:23
  * </pre>
  */
object TestIndexedRowMatrix {

  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local").setAppName("indexedRowMatrix")
    val sc = new SparkContext(conf)
    val rdd = sc.textFile("c:/a.txt").map(_.split(' ').map(_.toDouble).map(line => Vectors.dense(line)).map((vd) => new IndexedRow(vd.size, vd)))
    val irm = new IndexedRowMatrix(rdd)
    println(irm.getClass)
    println(irm.rows.foreach(println))
  }

}
