package learn.type_parameterization

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-04 9:36
  * </pre>
  */
class Pair[T <: Comparable[T]](val first: T,second: T){
  def bigger = if(first.compareTo(second) > 0) first else second
}

class Pair_Lower_Bound[T](val first: T,val second: T){
  def replaceFirst[R >: T](newFirst: R) = new Pair_Lower_Bound[R](newFirst,second)
}


object Type_Variables_Bounds {

  def main(args: Array[String]): Unit = {
    val pair = new Pair("Spark","Hadoop")
    println(pair.bigger)

    val bound = new Pair_Lower_Bound("Spark","Hadoop")
    println(bound.replaceFirst().second)
    println(bound.replaceFirst("zzzzz").first)
  }

}
