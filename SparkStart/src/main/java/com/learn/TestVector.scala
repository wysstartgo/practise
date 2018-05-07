package com.learn

import org.apache.spark.mllib.linalg
import org.apache.spark.mllib.linalg.Vectors


/**
  * <pre>
  *
  * 【标题】: 向量
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-04 9:30
  * </pre>
  */
object TestVector {

  def main(args: Array[String]): Unit = {
    //建立密集向量
    val vd: linalg.Vector = Vectors.dense(2,0,6)
    println(vd(2))
    //建立稀疏向量
    //第一个参数是输入数据的大小    第二个是下标     第三个是数据值
    val vs: linalg.Vector = Vectors.sparse(4,Array(0,1,2,3),Array(9,5,2,7))
    println(vs(2))
  }
}
