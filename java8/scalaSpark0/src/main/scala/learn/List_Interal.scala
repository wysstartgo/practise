package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 15:54
  * </pre>
  */
object List_Interal {

  def main(args: Array[String]): Unit = {
    val list = List(1,2,3,4,5)
    val listAny: List[Any] = list
    println(list.isEmpty)
    println(list.head)
    println(list.tail)
    println(list.drop(3))
    println(list.map(_ * 3))
    println(list)
  }

}
