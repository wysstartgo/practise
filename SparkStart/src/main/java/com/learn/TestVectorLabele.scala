package com.learn

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.mllib.util.MLUtils

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 14:42
  * </pre>
  */
object TestVectorLabele {

  def main(args: Array[String]): Unit = {
    //testLabeledPoint1
    testLabelPoint2

  }

  def testLabeledPoint1: Unit ={
    //建立密集向量
    val vd: linalg.Vector = Vectors.dense(2, 0, 6)
    //对密集向量建立标记点
    val pos = LabeledPoint(1, vd)
    //打印标记点中的内容数据
    println(pos.features)
    //打印既定标记
    println(pos.label)
    //稀疏向量
    val vs: linalg.Vector = Vectors.sparse(4, Array(0, 1, 2, 3), Array(9, 5, 2, 7))
    val neg = LabeledPoint(2, vs)
    println(neg.features)
    println(neg.label)
  }

  def testLabelPoint2: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("testLabelPoint2")
    val sc = new SparkContext(conf)
    val mu = MLUtils.loadLibSVMFile(sc,"E:\\workspace\\SparkStart\\src\\main\\resources\\svm.txt")
    mu.foreach(println)
  }

}
