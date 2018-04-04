package learn


/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 19:01
  * </pre>
  */
object Higher_order_functions {

  def main(args: Array[String]): Unit = {

  }

  def function_f1(): Unit = {
    val fun = Math.ceil _
    val num = 3.14
    fun(num)

    Array(3.14,1.42,2.0).map(fun)

    val triple = (x:Double) => 3 * x

    Array(3.14,1.42,2.0).map((x:Double) => 3 * x)
  }
}
