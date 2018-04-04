package learn.type_parameterization

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-04 10:17
  * </pre>
  */
class Pair_Ordering[T: Ordering] (val first: T,val second: T){
  def bigger(implicit ordered: Ordering[T]) = {
    if(ordered.compare(first,second) > 0) first else second
  }
}


object Context_Bounds {

  def main(args: Array[String]): Unit = {
    val pair = new Pair_Ordering("Spark","Hadoop")
    println(pair.bigger)

    val pair_Int = new Pair_Ordering(3,5)

    println(pair_Int.bigger)
  }


}
