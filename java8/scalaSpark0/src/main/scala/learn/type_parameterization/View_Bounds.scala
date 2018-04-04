package learn.type_parameterization

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-04 10:21
  * </pre>
  */
class Pair_notPerect[T <% Comparable[T]](val first: T,val second: T){
  def bigger = if(first.compareTo(second) > 0) first else second
}

class Pair_Better[T <% Ordered[T]](val first: T,val second: T){
  def bigger = if(first > second) first else second
}

object View_Bounds {
  def main(args: Array[String]): Unit = {
    val pair = new Pair_notPerect("Spark","Hadoop")
    println(pair.bigger)

    val pairInt = new Pair_notPerect(3,5)
    println(pairInt.bigger)

    val pair_Better_String = new Pair_Better("Java","Scala")
    println(pair_Better_String.bigger)
  }
}
