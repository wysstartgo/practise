//package com.learn.streaming
//
//import org.apache.spark.SparkConf
//import org.apache.spark.streaming.StreamingContext
//import org.scalatest.time.Seconds
//
///**
//  * <pre>
//  *
//  * @title: StreamingTest
//  * @description:
//  * @company: 润投科技
//  * @author: wuys
//  * @datetime: 2019-04-30 16:13
//  *            </pre>
//  */
//object StreamingTest {
//
//  def main(args: Array[String]): Unit = {
//    val conf = new SparkConf().setMaster("local[1]").setAppName("StreamingTest")
//    val ssc = new StreamingContext(conf,Seconds(1))
//    val lines = ssc.socketTextStream("client",9999)
//    val words = lines.flatMap(_.split(" "))
//    val windowWords = words.window(Seconds(3),Seconds(1))
//    windowWords.print()
//    ssc.start()
//    ssc.awaitTermination()
//  }
////  Spark Streaming提供了滑动窗口操作的支持，
//////  从而让我们可以对一个滑动窗口内的数据执行计算操作。每次掉落在窗口内的RDD的数据，会被聚合起来执行计算操作，然后生成的RDD，
//////  会作为window DStream的一个RDD。
//////  比如下图中，就是对每三秒钟的数据执行一次滑动窗口计算，这3秒内的3个RDD会被聚合起来进行处理，然后过了两秒钟，又会对最近三秒内的数据执行滑动窗口计算。
//////  所以每个滑动窗口操作，都必须指定两个参数，窗口长度以及滑动间隔，而且这两个参数值都必须是batch间隔的整数倍。（Spark Streaming对滑动窗口的支持，是比Storm更加完善和强大的）
//////  Transform：转换
//////  window：对每个滑动窗口的数据执行自定义的计算
//////  countByWindow：对每个滑动窗口的数据执行count操作
//////  reduceByWindow：对每个滑动窗口的数据执行reduce操作
//////  reduceByKeyAndWindow：对每个滑动窗口的数据执行reduceByKey操作
//////  countByValueAndWindow：对每个滑动窗口的数据执行countByValue操作
//////  案例：
//  object WindowDemo {
//    def main(args: Array[String]): Unit = {
//      Logger.getLogger("org").setLevel(Level.WARN)
//      val config = new SparkConf().setAppName("WindowDemo").setMaster("local[2]")
//      //Seconds(1) 1秒创建一个RDD
//      val ssc = new StreamingContext(config, Seconds(1))
//      //(a: Int, b: Int) => a + b   a代表上一次累加的结果，b代表本次需要累加的元素
//      //Seconds(3)  代表窗口的时间范围
//      //Seconds(2)  代表窗口的滑动间隔
//      ssc.socketTextStream("hadoop01", 8888).flatMap(_.split(" ")).map((_, 1)).reduceByKeyAndWindow(
//        (a: Int, b: Int) => a + b, Seconds(3), Seconds(2)).print()
//      ssc.start()
//      ssc.awaitTermination()
//    }
//  }
//}
