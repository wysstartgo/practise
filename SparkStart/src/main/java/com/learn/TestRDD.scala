package com.learn

import org.apache.spark.{SparkConf, SparkContext}

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-05-03 15:13
  * </pre>
  */
object TestRDD {

  def main(args: Array[String]): Unit = {
    //test01();
    //test02
    //cacheTest()
    //cacheTest2()
    //cartesianTest
    //coalesceTest
    //repartitionTest
    //countByValue
    //countByKey
    //distinct
    //flatMapTest
    //groupByTest
    //keyByTest
    //reduceTest
    //rddMethodTest
    //sortByTest
    zipTest
  }

  def test01(){
    val conf = new SparkConf().setMaster("local").setAppName("TestRDD")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    val result = arr.aggregate(0)(math.max(_,_),_ + _)
    println(result)
  }

  def test02(): Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("TestRDD")
    val sc = new SparkContext(conf)
    //在这里，parallelize会将array分成两个部分存储    Array(1,2,3)     Array(4,5,6)
    val arr = sc.parallelize(Array(1,2,3,4,5,6),2)
    val result = arr.aggregate(0)(math.max(_,_),_ + _)
    println(result)
  }

  def cacheTest(): Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("CacheTest")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array("abc","b","c","d","e","f"))
    println(arr)
    println("-------------------------------------")
    println(arr.cache())
  }

  def cacheTest2(): Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("CacheTest2")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array("abc","b","c","d","e","f"))
    arr.foreach(println)
  }

  /**
    * 笛卡尔积
    */
  def cartesianTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("cartesianTest")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    var arr2 = sc.parallelize(Array(6,5,4,3,2,1))
    val result = arr.cartesian(arr2)
    result.foreach(print)
  }

  /**
    * 数据重新分片存储
    */
  def coalesceTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("coalesceTest")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    val arr2 = arr.coalesce(2,true)
    arr2.foreach(println)
    println("------------------------------")
    val result = arr.aggregate(0)(math.max(_,_),_ + _)
    println(result)
    //计算重新分区数据值
    val result2 = arr2.aggregate(0)(math.max(_,_),_ + _)
    println(result2)
  }

  /**
    * 另一种数据重新分片方法
    */
  def repartitionTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("repartitionTest")
    val sc = new SparkContext(conf)
    var arr = sc.parallelize(Array(1,2,3,4,5,6))
    arr = arr.repartition(3)
    arr.foreach(println)
    println("--------------------------------")
    println(arr.partitions.length)
  }

  /**
    * 统计
    */
  def countByValue: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("countByValue")
    val sc = new SparkContext(conf)
    val arr = sc.parallelize(Array(1,2,3,4,5,6))
    val result = arr.countByValue()
    result.foreach(print)
  }


  def countByKey: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("countByKey")
    val sc = new SparkContext(conf)
    //创建数据集
    var arr = sc.parallelize(Array((1,"cool"),(2,"good"),(1,"bad"),(1,"fine")))
    val result = arr.countByKey()
    result.foreach(print)
  }

  /**
    * 去重
    */
  def distinct: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("distinct")
    val sc = new SparkContext(conf)
    var arr = sc.parallelize(Array(("cool"),("good"),("bad"),("fine"),("good"),("cool")))
    val result = arr.distinct()
    result.foreach(println)
  }

  /**
    * flatMap
    */
  def flatMapTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("flatMapTest")
    val sc = new SparkContext(conf)
    var arr = sc.parallelize(Array(1,2,3,4,5))
    val result = arr.flatMap(x => List(x + 1)).collect()
    result.foreach(println)
  }

  /**
    *
    */
  def groupByTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("groupBy")
    val sc = new SparkContext(conf)
    var arr = sc.parallelize(Array(1,2,3,4,5))
    val group1 = arr.groupBy((num:Int) => num >= 3,1)
    group1.foreach(println)
    println("-------------------------------")
    val group2 = arr.groupBy((num:Int) => num < 3,2)
    group2.foreach(println)
  }

  /**
    * 生成键值对
    */
  def keyByTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("keyBy")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array("one","two","three","four","five"))
    val str2 = str.keyBy(word => word.size)
    str2.foreach(println)
  }

  /**
    * 进行reduce操作
    */
  def reduceTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("reduceTest")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array("one","two","three","four","five"))
    val result = str.reduce(_ + _)
    result.foreach(print)
  }

  /**
    * 测试rdd method
    */
  def rddMethodTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("rddMethodTest")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array("one","two","three","four","five"))
    val result = str.reduce((str1:String,str2:String) => if(str2.size > str1.size) str2 else str1)
    result.foreach(println)
  }

  /**
    * 排序
    */
  def sortByTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("sortBy")
    val sc = new SparkContext(conf)
    var str = sc.parallelize(Array((5,"b"),(6,"a"),(1,"f"),(3,"d"),(4,"c"),(2,"e")))
    str = str.sortBy(word => word._1,true)
    val str2 = str.sortBy(word => word._2,true)
    str.foreach(print)
    str2.foreach(print)
  }

  /**
    * zip压缩
    */
  def zipTest: Unit ={
    val conf = new SparkConf().setMaster("local").setAppName("zipTest")
    val sc = new SparkContext(conf)
    val arr1 = Array(1,2,3,4,5,6)
    val arr2 = Array("a","b","c","d","e","f")
    val arr3 = Array("g","h","i","j","k","l")
    val arr4 = arr1.zip(arr2).zip(arr3)
    arr4.foreach(print)
  }

}
