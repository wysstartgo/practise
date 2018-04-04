package learn

/**
  * <pre>
  *
  * 【标题】: 
  * 【描述】: 
  * 【版权】: 润投科技
  * 【作者】: wuys
  * 【时间】: 2018-04-03 18:49
  * </pre>
  */
object Curring {

  def main(args: Array[String]): Unit = {
    def multiple(x:Int,y:Int) = x * y
    def multipleOne(x:Int) = (y:Int) => x * y
    println(multipleOne(3)(4))

    def curring(x: Int)(y: Int) = x * y
    println(curring(3)(4))
    val a = Array("Hello","Spark")
    val b = Array("hello","spark")

    println(a.corresponds(b)(_.equalsIgnoreCase(_))) //不磁大小写的equal
  }

}
