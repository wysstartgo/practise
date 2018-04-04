package learn

import scala.collection.immutable.TreeMap
import scala.collection.mutable

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 18:28
  * </pre>
  */
object Set_Map {

  def main(args: Array[String]): Unit = {
    val data = mutable.Set.empty[Int]
    data ++= List(1,2,3)
    data += 4
    data --= List(2,3)
    println(data)
    data += 1
    println(data)
    data.clear()
    println(data)

    val map = mutable.Map.empty[String,String]
    map("Java") = "Hadoop"
    map("Scala") = "Spark"
    println(map)
    println(map("Scala"))

    val treeSet = mutable.TreeSet("Spark","Scala","Hadoop")
    println(treeSet)
    val treeSetForChar = mutable.TreeSet("Spark","Scala","Hadoop")
    println(treeSetForChar)

    var treeMap = TreeMap("Scala" -> "Spark","Java" -> "Hadoop")
    println(treeMap)
  }

}
